package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.learningresource.LearningResource;
import com.edutie.backend.domain.core.learningresource.identities.LearningResourceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningResourceRepository extends JpaRepository<LearningResource, LearningResourceId> {
}
