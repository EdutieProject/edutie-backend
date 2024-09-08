package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.common.RoleRepository;

public interface AdministratorRepository extends RoleRepository<Administrator, AdministratorId> { }
