package com.edutie.mocks.persistence.learningexperience.base;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import validation.Result;
import validation.WrapperResult;

public class MockLearningExperiencePersistenceBase implements LearningExperiencePersistence {
    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param learningExperienceId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<LearningExperience<?>> getById(LearningExperienceId learningExperienceId) {
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
    public Result save(LearningExperience<?> entity) {
        return null;
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param learningExperienceId entity id
     * @return Result object
     */
    @Override
    public Result removeById(LearningExperienceId learningExperienceId) {
        return null;
    }

    /**
     * Removes the following entity from the database. This is a default method, which when not
     * override is correlated with removeById() method.
     *
     * @param entity entity
     * @return Result
     */
    @Override
    public Result remove(LearningExperience<?> entity) {
        return null;
    }
}
