package com.edutie.application.profiles.user.implementation;

import com.edutie.api.common.AuthenticationError;
import com.edutie.application.profiles.user.GetUserDetailsQueryHandler;
import com.edutie.application.profiles.user.query.GetUserDetailsQuery;
import com.edutie.application.profiles.user.viewmodels.UserDetails;
import com.edutie.domain.core.administration.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserDetailsQueryHandlerImplementation implements GetUserDetailsQueryHandler {
    @Override
    public WrapperResult<UserDetails> handle(GetUserDetailsQuery query) {
        if (query.authentication() instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            UserId userId = new UserId(UUID.fromString(jwtAuthenticationToken.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
            return WrapperResult.successWrapper(
                    new UserDetails(
                            userId,
                            (String) jwtAuthenticationToken.getTokenAttributes().get("preferred_username"),
                            (String) jwtAuthenticationToken.getTokenAttributes().get("given_name")
                    )
            );
        }
        return WrapperResult.failureWrapper(AuthenticationError.noJwtAuthentication());
    }
}
