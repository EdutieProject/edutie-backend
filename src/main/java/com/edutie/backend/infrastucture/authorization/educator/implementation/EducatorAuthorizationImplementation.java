package com.edutie.backend.infrastucture.authorization.educator.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.infrastucture.authorization.AuthorizationError;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import validation.Result;

@Component
@RequiredArgsConstructor
public class EducatorAuthorizationImplementation implements EducatorAuthorization {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final EducatorRepository educatorRepository;

    @Override
    public Result authorize(UserId userId) {
        return educatorRepository.findByOwnerUserId(userId).isEmpty() ?
                Result.failure(AuthorizationError.roleExpected(Educator.class))
                : Result.success();
    }

    /**
     * Pre-inject roles if any injectable exist in authentication token.
     *
     * @param authentication authentication token
     */
    @Override
    public void injectRoles(JwtAuthenticationToken authentication) {
        LOGGER.debug("Educator authorization invoked. No role injection invoked.");
    }
}
