package com.edutie.backend.infrastucture.persistence.implementation.persistence.studyprogram;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SciencePersistenceImplementation implements SciencePersistence {
    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param scienceId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Science> getById(ScienceId scienceId) {
        return null;
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
    public Result save(Science entity) {
        return null;
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
        return null;
    }

    /**
     * Retrieve all sciences
     *
     * @return Science list
     */
    @Override
    public List<Science> getAll() {
        return null;
    }
}
