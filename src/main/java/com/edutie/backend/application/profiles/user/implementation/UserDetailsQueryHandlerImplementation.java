package com.edutie.backend.application.profiles.user.implementation;

import com.edutie.backend.api.common.AuthenticationError;
import com.edutie.backend.application.profiles.user.UserDetailsQueryHandler;
import com.edutie.backend.application.profiles.user.query.UserDetailsQuery;
import com.edutie.backend.application.profiles.user.viewmodels.UserDetails;
import com.edutie.backend.domain.administration.UserId;
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
public class UserDetailsQueryHandlerImplementation implements UserDetailsQueryHandler {
    @Override
    public WrapperResult<UserDetails> handle(UserDetailsQuery query) {
        if (query.authentication() instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            UserId userId = new UserId(UUID.fromString(jwtAuthenticationToken.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
            return WrapperResult.successWrapper(
                    new UserDetails(
                            userId,
                            (String) jwtAuthenticationToken.getTokenAttributes().get("given_name"),
                            (String) jwtAuthenticationToken.getTokenAttributes().get("family_name"),
                            (String) jwtAuthenticationToken.getTokenAttributes().get("name")
                    )
            );
        }
        return WrapperResult.failureWrapper(AuthenticationError.noJwtAuthentication());
    }
}
