package com.edutie.backend.infrastucture.persistence.implementation.education;

import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.education.exercisetype.persistence.ExerciseTypePersistence;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.ExerciseTypeRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.ReportTemplateParagraphRepository;
import validation.Result;
import validation.WrapperResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseTypePersistenceImplementation implements ExerciseTypePersistence {
	private final ExerciseTypeRepository exerciseTypeRepository;
	private final ReportTemplateParagraphRepository reportTemplateParagraphRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<ExerciseType, ExerciseTypeId> getRepository() {
		return exerciseTypeRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<ExerciseType> entityClass() {
		return ExerciseType.class;
	}

	/**
	 * Retrieve all exercise types
	 *
	 * @return Wrapper result of desired list
	 */
	@Override
	public WrapperResult<List<ExerciseType>> getAll() {
		return WrapperResult.successWrapper(exerciseTypeRepository.findAll());
	}

	@Override
	public Result save(ExerciseType exerciseType) {
		try {
			reportTemplateParagraphRepository.saveAll(exerciseType.getReportTemplate());
			exerciseTypeRepository.save(exerciseType);
			return Result.success();
		} catch (Exception exception) {
			return Result.failure(PersistenceError.exceptionEncountered(exception));
		}
	}
}
