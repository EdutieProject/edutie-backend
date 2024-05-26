package com.edutie.backend.api.common;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.authorization.base.AuthorizationBase;
import org.springframework.http.ResponseEntity;
import validation.Result;
import validation.WrapperResult;

import java.util.function.Supplier;

public class GenericRequestHandler<TResponseBody, TAuthorization extends AuthorizationBase> {
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
                : ResponseEntity.status(400).body(ApiResult.fromWrapper(wrapperResult));
    }

    private ResponseEntity<ApiResult<TResponseBody>> translateResult(Result result) {
        return result.isSuccess() ?
                ResponseEntity.ok(ApiResult.fromResult(result))
                : ResponseEntity.status(400).body(ApiResult.fromResult(result));
    }
}
