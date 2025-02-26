package com.edutie.infrastructure.authorization.administrator.implementation;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.infrastructure.authorization.AuthorizationError;
import com.edutie.infrastructure.authorization.administrator.AdministratorAuthorization;
import com.edutie.infrastructure.persistence.jpa.repositories.AdministratorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.Result;
import org.springframework.security.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.stereotype.*;
import lombok.*;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdministratorAuthorizationImplementation implements AdministratorAuthorization {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final AdministratorRepository administratorRepository;

	private static final String IDENTITY_PROVIDER_EDUTIE_ADMIN_ROLE = "edutie-admin";

	@Override
	public Result authorize(UserId userId) {
		return administratorRepository.findByOwnerUserId(userId).isPresent() ? Result.success() : Result.failure(AuthorizationError.roleExpected(Administrator.class));
	}

	/**
	 * Pre-inject roles if any injectable exist in authentication token.
	 *
	 * @param authentication authentication token
	 */
	@Override
	public synchronized void injectRoles(JwtAuthenticationToken authentication) {
		UserId userId = new UserId(UUID.fromString(authentication.getTokenAttributes().get(JwtClaimNames.SUB).toString()));

		boolean tokenHasAdminRole = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(o -> o.equals(IDENTITY_PROVIDER_EDUTIE_ADMIN_ROLE));
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
