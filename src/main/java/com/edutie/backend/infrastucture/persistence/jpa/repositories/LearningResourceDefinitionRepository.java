package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface LearningResourceDefinitionRepository extends JpaRepository<LearningResourceDefinition, LearningResourceDefinitionId> {
	List<LearningResourceDefinition> getByAuthorEducator(Educator educator);

	@Query(value = "SELECT lrd FROM LearningResourceDefinition lrd ORDER BY FUNCTION('RANDOM') LIMIT 1")
	Optional<LearningResourceDefinition> findRandom();
}
