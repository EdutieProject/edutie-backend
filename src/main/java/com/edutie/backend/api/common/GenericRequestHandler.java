package com.edutie.backend.api.common;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.authorization.base.AuthorizationBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class GenericRequestHandler<TResponseBody> {
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private Result authenticationResult = null;
    private Result authorizationResult = null;
    private UserId actionUserId = null;
    private JwtAuthenticationToken authenticationToken = null;

    public GenericRequestHandler<TResponseBody> authenticate(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            LOGGER.info("Incoming request has no valid authentication");
            this.authenticationResult = Result.failure(AuthenticationError.invalidAuthentication());
            return this;
        }
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            this.authenticationToken = jwtAuthenticationToken;
            this.actionUserId = new UserId(UUID.fromString(jwtAuthenticationToken.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
            this.authenticationResult = Result.success();
            return this;
        }
        LOGGER.warn("Authentication of provided request is valid, but could not be resolved as a JWT");
        this.authenticationResult = Result.failure(AuthenticationError.noJwtAuthentication());
        return this;
    }

    public <TAuthorization extends AuthorizationBase> GenericRequestHandler<TResponseBody> authorize(TAuthorization authorization) {
        if (authenticationToken != null)
            authorization.injectRoles(this.authenticationToken);
        this.authorizationResult = authorization.authorize(actionUserId);
        return this;
    }

    public <U extends Result> ResponseEntity<ApiResult<TResponseBody>> handle(Function<UserId, U> resultLambda) {
        if (authenticationResult.isFailure())
            return ResponseEntity.status(401).body(ApiResult.fromResult(authenticationResult));
        if (authorizationResult.isFailure())
            return ResponseEntity.status(403).body(ApiResult.fromResult(authorizationResult));

        U handlerResult = resultLambda.apply(actionUserId);

        if (handlerResult.getClass() != Result.class) {
            WrapperResult<TResponseBody> wrapperResult = (WrapperResult<TResponseBody>) handlerResult;
            return translateWrapper(wrapperResult);
        } else
            return translateResult(handlerResult);
    }

    public <U extends Result> ResponseEntity<ApiResult<TResponseBody>> handle(Supplier<U> resultSupplier) {
        return handle((userId) -> resultSupplier.get());
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
            return Integer.parseInt(errorCodeSplit[errorCodeSplit.length - 1]);
        } catch (Exception ignored) {
            LOGGER.error("Could not translate error code. Proceeding with 500 error code");
            return 500;
        }
    }
}
