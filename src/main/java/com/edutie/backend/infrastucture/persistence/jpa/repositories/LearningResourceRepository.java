package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface LearningResourceRepository extends JpaRepository<LearningResource, LearningResourceId> {
	List<LearningResource> getAllByDefinition(LearningResourceDefinition definition);
}
