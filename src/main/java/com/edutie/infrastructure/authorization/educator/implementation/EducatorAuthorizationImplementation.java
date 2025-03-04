package com.edutie.infrastructure.authorization.educator.implementation;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import com.edutie.infrastructure.authorization.AuthorizationError;
import com.edutie.infrastructure.authorization.educator.EducatorAuthorization;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import validation.Result;
import org.springframework.security.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

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
		boolean adminRoleIsInToken = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains("edutie-admin");
		boolean adminProfileDoesNotExist = administratorRepository.findByOwnerUserId(userId).isEmpty();

		if (adminRoleIsInToken && adminProfileDoesNotExist) {
			log.info("Injecting administrator & educator role for user {}", userId);
			Administrator administrator = administratorRepository.save(Administrator.create(userId));
			Educator educator = Educator.create(userId);
			educator.setType(EducatorType.ADMINISTRATOR);
			educatorRepository.save(educator);
		}
	}
}
