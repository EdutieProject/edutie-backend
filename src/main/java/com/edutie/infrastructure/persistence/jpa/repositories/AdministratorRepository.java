package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.administration.administrator.identities.AdministratorId;
import com.edutie.infrastructure.persistence.jpa.repositories.common.RoleRepository;

public interface AdministratorRepository extends RoleRepository<Administrator, AdministratorId> { }
