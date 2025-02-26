package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.administration.administrator.identities.AdministratorId;
import com.edutie.domain.core.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.infrastructure.persistence.jpa.repositories.AdministratorRepository;
import com.edutie.infrastructure.persistence.jpa.repositories.common.RoleRepository;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class AdministratorPersistenceImplementation implements AdministratorPersistence {
	private final AdministratorRepository administratorRepository;

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<Administrator> entityClass() {
		return Administrator.class;
	}

	@Override
	public RoleRepository<Administrator, AdministratorId> getRepository() {
		return administratorRepository;
	}
}
