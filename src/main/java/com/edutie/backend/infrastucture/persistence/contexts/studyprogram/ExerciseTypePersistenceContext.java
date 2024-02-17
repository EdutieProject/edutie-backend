package com.edutie.backend.infrastucture.persistence.contexts.studyprogram;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.exercisetype.ExerciseType;
import com.edutie.backend.domain.studyprogram.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface ExerciseTypePersistenceContext extends PersistenceContext<ExerciseType, ExerciseTypeId> {
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
