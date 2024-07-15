package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.common.RoleRepository;

public interface EducatorRepository extends RoleRepository<Educator, EducatorId> {
}
