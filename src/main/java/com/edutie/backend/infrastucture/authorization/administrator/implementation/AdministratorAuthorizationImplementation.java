package com.edutie.backend.infrastucture.authorization.administrator.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.infrastucture.authorization.AuthorizationError;
import com.edutie.backend.infrastucture.authorization.administrator.AdministratorAuthorization;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import validation.Result;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdministratorAuthorizationImplementation implements AdministratorAuthorization {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final AdministratorRepository administratorRepository;

    @Override
    public Result authorize(UserId userId) {
        return administratorRepository.findByOwnerUserId(userId).isPresent() ?
                Result.success() : Result.failure(AuthorizationError.roleExpected(Administrator.class));
    }

    /**
     * Pre-inject roles if any injectable exist in authentication token.
     *
     * @param authentication authentication token
     */
    @Override
    public void injectRoles(JwtAuthenticationToken authentication) {
        UserId userId = new UserId(UUID.fromString(authentication.getTokenAttributes().get(JwtClaimNames.SUB).toString()));

        boolean tokenHasAdminRole = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(o -> o.equals("edutie-admin"));
        boolean noAdminRoleInEdutie = administratorRepository.findByOwnerUserId(userId).isEmpty();

        if (tokenHasAdminRole && noAdminRoleInEdutie) {
            LOGGER.info("Admin role spotted inside the jwt. Injecting admin role in the edutie db.");
            Administrator administratorProfile = Administrator.create(userId);
            administratorRepository.save(administratorProfile);
            return;
        }
        LOGGER.debug("Role injection conditions not met. Proceeding without any operations regarding roles.");
    }
}
