package com.edutie.backend.infrastucture.persistence.implementation.persistence.education;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.education.exercisetype.persistence.ExerciseTypePersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ExerciseTypeRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExerciseTypePersistenceImplementation implements ExerciseTypePersistence {
    private final ExerciseTypeRepository exerciseTypeRepository;

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param exerciseTypeId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<ExerciseType> getById(ExerciseTypeId exerciseTypeId) {
        try {
            Optional<ExerciseType> optionalCourse = exerciseTypeRepository.findById(exerciseTypeId);
            return optionalCourse
                    .map(Result::successWrapper)
                    .orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(ExerciseType.class)));
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @param entity
     * @return Result object
     */
    @Override
    public Result save(ExerciseType entity) {
        try {
            exerciseTypeRepository.save(entity);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param exerciseTypeId entity id
     * @return Result object
     */
    @Override
    public Result removeById(ExerciseTypeId exerciseTypeId) {
        try {
            exerciseTypeRepository.deleteById(exerciseTypeId);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
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

    /**
     * Retrieve all exercise types associated with given user
     *
     * @param userId user id
     * @return Wrapper result of desired list
     */
    @Override
    public WrapperResult<List<ExerciseType>> getAllOfUserId(UserId userId) {
        return WrapperResult.failureWrapper(new Error("No impl", "Not implemented - refactor required"));
    }
}
