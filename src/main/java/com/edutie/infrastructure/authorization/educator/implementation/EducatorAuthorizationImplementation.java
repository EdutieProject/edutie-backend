package com.edutie.infrastructure.authorization.educator.implementation;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import com.edutie.infrastructure.authorization.AuthorizationError;
import com.edutie.infrastructure.authorization.educator.EducatorAuthorization;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import validation.Result;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class EducatorAuthorizationImplementation implements EducatorAuthorization {
    private final EducatorRepository educatorRepository;
    private final AdministratorRepository administratorRepository;

    @Override
    public Result authorize(UserId userId) {
        return educatorRepository.findByOwnerUserId(userId).isEmpty() ? Result.failure(AuthorizationError.roleExpected(Educator.class)) : Result.success();
    }

    /**
     * Pre-inject roles if any injectable exist in authentication token.
     *
     * @param authentication authentication token
     */
    @Override
    public synchronized void injectRoles(JwtAuthenticationToken authentication) {
        UserId userId = new UserId(UUID.fromString(authentication.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
        if (educatorRepository.findByOwnerUserId(userId).isEmpty()) {
            log.info("No educator role profile found in edutie context. Injecting educator role in the edutie db.");
            Educator educatorProfile = Educator.create(userId);
            boolean adminRoleIsInToken = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains("edutie-admin");
            boolean adminProfileDoesNotExist = administratorRepository.findByOwnerUserId(userId).isEmpty();
            if (adminRoleIsInToken) {
                log.info("Choosing administrator educator type for injection for user {}", userId);
                if (adminProfileDoesNotExist) {
                    log.info("Injecting administrator profile for user {}", userId);
                    administratorRepository.save(Administrator.create(userId));
                }
                educatorProfile.setType(EducatorType.ADMINISTRATOR);
            }
            educatorRepository.save(educatorProfile);
            return;
        }
        log.debug("Educator role already exits. Not injecting educator role for this request.");
    }
}
