package com.edutie.backend.api.common;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.authorization.base.AuthorizationBase;
import org.springframework.http.ResponseEntity;
import validation.Result;
import validation.WrapperResult;

import java.util.function.Supplier;

public class GenericRequestHandler<TResult extends Result, TAuthorization extends AuthorizationBase> {
    private TAuthorization authorization;
    private UserId actionUserId;

    public GenericRequestHandler<TResult, TAuthorization> authorize(UserId actionUserId, TAuthorization authorization) {
        this.actionUserId = actionUserId;
        this.authorization = authorization;
        return this;
    }

    public ResponseEntity<?> handle(Supplier<TResult> resultSupplier) {
        Result authorizationResult = authorization.authorize(actionUserId);
        if (authorizationResult.isFailure())
            return ResponseEntity.status(403).body(authorizationResult);
        TResult handlerResult = resultSupplier.get();
        return switch (handlerResult) {
            case WrapperResult<?> wrapperResult -> translateWrapper(wrapperResult);
            case Result result -> translateResult(result);
        };
    }

    private ResponseEntity<?> translateWrapper(WrapperResult<?> result) {
        return result.isSuccess() ?
                ResponseEntity.ok(result.getValue())
                : ResponseEntity.status(400).body(result.flatten());
    }

    private ResponseEntity<?> translateResult(Result result) {
        return result.isSuccess() ?
                ResponseEntity.ok(result)
                : ResponseEntity.status(400).body(result);
    }
}
