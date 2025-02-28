package com.edutie.infrastructure.persistence.implementation.personalization;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.domain.core.learning.student.identities.StudentId;
import com.edutie.infrastructure.persistence.PersistenceError;
import com.edutie.infrastructure.persistence.jpa.repositories.LearningResourceRepository;
import org.springframework.data.domain.Limit;
import validation.Result;
import validation.WrapperResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LearningExperiencePersistenceImplementation implements LearningExperiencePersistence {
	private final LearningResourceRepository learningResourceRepository;
	private final LearningResourceDefinitionRepository learningResourceDefinitionRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<LearningExperience, LearningExperienceId> getRepository() {
		return learningResourceRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<LearningExperience> entityClass() {
		return LearningExperience.class;
	}

	/**
	 * Retrieve all Learning Resources associated with given definition
	 *
	 * @param learningResourceDefinitionId definition id
	 * @return Wrapper result of desired list
	 */
	@Override
	public WrapperResult<List<LearningExperience>> getByLearningResourceDefinitionId(LearningResourceDefinitionId learningResourceDefinitionId) {
		try {
			Optional<StaticLearningResourceDefinition> definitionOptionalWrapper = learningResourceDefinitionRepository.findById(learningResourceDefinitionId);
			return definitionOptionalWrapper.map(definition -> WrapperResult.successWrapper(learningResourceRepository.getAllByDefinitionId(learningResourceDefinitionId))).orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(StaticLearningResourceDefinition.class)));
		} catch (Exception ex) {
			return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
		}
	}

	/**
	 * Retrieves latest learning resources made for the student.
	 *
	 * @param studentId student id
	 * @return Wrapper result of desired list
	 */
	@Override
	public WrapperResult<List<LearningExperience>> getLatestForStudent(StudentId studentId) {
		try {
			return WrapperResult.successWrapper(learningResourceRepository.getAllByStudentIdOrderByCreatedOnDesc(studentId, Limit.of(10)));
		} catch (Exception ex) {
			return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
		}
	}
}
