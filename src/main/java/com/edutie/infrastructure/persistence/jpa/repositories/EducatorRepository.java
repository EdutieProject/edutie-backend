package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.identities.EducatorId;
import com.edutie.infrastructure.persistence.jpa.repositories.common.RoleRepository;

public interface EducatorRepository extends RoleRepository<Educator, EducatorId> { }
