package com.edutie.backend.domain.education.exercisetype.persistence;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.WrapperResult;

import java.util.List;

public interface ExerciseTypePersistence extends Persistence<ExerciseType, ExerciseTypeId> {
    /**
     * Retrieve all exercise types
     * @return Wrapper result of desired list
     */
    WrapperResult<List<ExerciseType>> getAll();

    /**
     * Retrieve all exercise types associated with given educator
     * @param userId user id
     * @return Wrapper result of desired list
     */
    WrapperResult<List<ExerciseType>> getAllOfUserId(UserId userId);
}
