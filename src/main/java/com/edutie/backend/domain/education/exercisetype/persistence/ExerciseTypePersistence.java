package com.edutie.backend.domain.education.exercisetype.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import validation.WrapperResult;

import java.util.List;

public interface ExerciseTypePersistence extends Persistence<ExerciseType, ExerciseTypeId> {
	/**
	 * Retrieve all exercise types
	 *
	 * @return Wrapper result of desired list
	 */
	WrapperResult<List<ExerciseType>> getAll();
}
