package com.edutie.backend.api.common;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.authorization.base.AuthorizationBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

import java.util.function.Supplier;

public class GenericRequestHandler<TResponseBody, TAuthorization extends AuthorizationBase> {
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private TAuthorization authorization;
    private UserId actionUserId;

    public GenericRequestHandler<TResponseBody, TAuthorization> authorize(UserId actionUserId, TAuthorization authorization) {
        this.actionUserId = actionUserId;
        this.authorization = authorization;
        return this;
    }

    public <U extends Result> ResponseEntity<ApiResult<TResponseBody>> handle(Supplier<U> resultSupplier) {
        Result authorizationResult = authorization.authorize(actionUserId);

        if (authorizationResult.isFailure())
            return ResponseEntity.status(403).body(ApiResult.fromResult(authorizationResult));

        U handlerResult = resultSupplier.get();

        //TODO: rozwiązanie sklejone taśmą - naprawić
        if (handlerResult.getClass() != Result.class) {
            WrapperResult<TResponseBody> wrapperResult = (WrapperResult<TResponseBody>) handlerResult;
            return translateWrapper(wrapperResult);
        } else
            return translateResult(handlerResult);
    }

    private ResponseEntity<ApiResult<TResponseBody>> translateWrapper(WrapperResult<TResponseBody> wrapperResult) {
        return wrapperResult.isSuccess() ?
                ResponseEntity.ok(ApiResult.fromWrapper(wrapperResult))
                : ResponseEntity
                .status(inferStatusCode(wrapperResult.getError()))
                .body(ApiResult.fromWrapper(wrapperResult));
    }

    private ResponseEntity<ApiResult<TResponseBody>> translateResult(Result result) {
        return result.isSuccess() ?
                ResponseEntity.ok(ApiResult.fromResult(result))
                : ResponseEntity
                .status(inferStatusCode(result.getError()))
                .body(ApiResult.fromResult(result));
    }

    private int inferStatusCode(Error error) {
        try {
            String[] errorCodeSplit = error.code().split("-");
            return Integer.parseInt(errorCodeSplit[errorCodeSplit.length-1]);
        } catch (Exception ignored) {
            LOGGER.warn("Could not translate error code. Proceeding with 400 error code");
            return 400;
        }
    }
}
