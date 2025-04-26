package com.edutie.infrastructure.persistence.implementation.profiles.repositories;

import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.administration.administrator.identities.AdministratorId;
import com.edutie.infrastructure.persistence.implementation.common.repositories.RoleRepository;

public interface AdministratorRepository extends RoleRepository<Administrator, AdministratorId> { }
