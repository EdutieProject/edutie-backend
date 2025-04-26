package com.edutie.infrastructure.persistence.implementation.profiles.repositories;

import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import com.edutie.infrastructure.persistence.implementation.common.repositories.RoleRepository;

public interface EducatorRepository extends RoleRepository<Educator, EducatorId> { }
