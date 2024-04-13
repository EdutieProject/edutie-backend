package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducatorRepository extends JpaRepository<Educator, EducatorId> {
    List<Educator> findEducatorsByCreatedBy(UserId userId);
}
