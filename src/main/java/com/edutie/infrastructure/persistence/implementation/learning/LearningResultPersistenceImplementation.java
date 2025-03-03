package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult.OnlineDiscussionSimulationActivityLearningResultRepository;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult.ScenarioProblemSolvingActivityLearningResultRepository;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult.SimpleProblemActivityLearningResultRepository;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult.StoryBasedActivityLearningResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;


@Component
@RequiredArgsConstructor
public class LearningResultPersistenceImplementation implements LearningResultPersistence {
    private final OnlineDiscussionSimulationActivityLearningResultRepository onlineDiscussionSimulationActivityLearningResultRepository;
    private final ScenarioProblemSolvingActivityLearningResultRepository scenarioProblemSolvingActivityLearningResultRepository;
    private final SimpleProblemActivityLearningResultRepository simpleProblemActivityLearningResultRepository;
    private final StoryBasedActivityLearningResultRepository storyBasedActivityLearningResultRepository;

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param learningResultId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<LearningResult<?>> getById(LearningResultId learningResultId) {
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
    public Result save(LearningResult<?> entity) {
        return null;
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param learningResultId entity id
     * @return Result object
     */
    @Override
    public Result removeById(LearningResultId learningResultId) {
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
    public Result remove(LearningResult<?> entity) {
        return null;
    }

}
