package com.edutie.backend.infrastucture.authorization.educator.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.infrastucture.authorization.AuthorizationError;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.AdministratorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import validation.Result;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EducatorAuthorizationImplementation implements EducatorAuthorization {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final EducatorRepository educatorRepository;
    private final AdministratorRepository administratorRepository;

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
        UserId userId = new UserId(UUID.fromString(authentication.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
        boolean adminRoleIsInToken = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains("edutie-admin");
        boolean adminProfileDoesNotExist = administratorRepository.findByOwnerUserId(userId).isEmpty();

        if (adminRoleIsInToken && adminProfileDoesNotExist) {
            LOGGER.info("Injecting administrator & educator role for user {}", userId);
            Administrator administrator = administratorRepository.save(Administrator.create(userId));
            Educator educator = Educator.create(userId, administrator);
            educator.setType(EducatorType.ADMINISTRATOR);
            educatorRepository.save(educator);
        }
    }
}
