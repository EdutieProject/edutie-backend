package com.edutie.backend.infrastucture.persistence.implementation.profiles;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.AdministratorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.common.RoleRepository;
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
