package com.edutie.backend.infrastucture.persistence.implementation.persistence.education;

import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.education.exercisetype.persistence.ExerciseTypePersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ExerciseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseTypePersistenceImplementation implements ExerciseTypePersistence {
    private final ExerciseTypeRepository exerciseTypeRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<ExerciseType, ExerciseTypeId> getRepository() {
        return null;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<ExerciseType> entityClass() {
        return null;
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
}
