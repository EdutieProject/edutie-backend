package com.edutie.backend.domain.education.exercisetype.persistence;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;

import java.util.List;

public interface ExerciseTypePersistence extends PersistenceBase<ExerciseType, ExerciseTypeId> {
    /**
     * Retrieve all exercise types
     * @return Exercise Type list
     */
    List<ExerciseType> getAll();

    /**
     * Retrieve all exercise types associated with given user
     * @param userId user id
     * @return Exercise Type list
     */
    List<ExerciseType> getAllOfUserId(UserId userId);
}
