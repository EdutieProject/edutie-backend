package com.edutie.backend.infrastructure.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface LearningResourceDefinitionRepository extends JpaRepository<StaticLearningResourceDefinition, LearningResourceDefinitionId> {
	List<StaticLearningResourceDefinition> getByAuthorEducator(Educator educator);

	@Query(value = "SELECT lrd FROM StaticLearningResourceDefinition lrd ORDER BY FUNCTION('RANDOM') LIMIT 1")
	Optional<StaticLearningResourceDefinition> findRandom();
}
