package com.edutie.backend.infrastucture.persistence.implementation.persistence.studyprogram;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SciencePersistenceImplementation implements SciencePersistence {
    private final ScienceRepository scienceRepository;
    /**
     * Retrieves the entity using its identifier. Entity is wrapped, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param scienceId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Science> getById(ScienceId scienceId) {
        try {
            Optional<Science> scienceOptional = scienceRepository.findById(scienceId);
            return scienceOptional.map(WrapperResult::successWrapper)
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Science.class)));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @param entity entity
     * @return Result object
     */
    @Override
    public Result save(Science entity) {
        try {
            scienceRepository.saveAndFlush(entity);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param scienceId entity id
     * @return Result object
     */
    @Override
    public Result removeById(ScienceId scienceId) {
        try {
            scienceRepository.deleteById(scienceId);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieve all sciences
     *
     * @return Science list
     */
    @Override
    public WrapperResult<List<Science>> getAll() {
        try {
            List<Science> sciences = scienceRepository.findAll();
            return WrapperResult.successWrapper(sciences);
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }
}
