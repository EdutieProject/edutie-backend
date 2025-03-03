package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.OnlineDiscussionSimulationActivityLearningExperienceRepository;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.ScenarioProblemSolvingActivityLearningExperienceRepository;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.SimpleProblemActivityLearningExperienceRepository;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.StoryBasedActivityLearningExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class LearningExperiencePersistenceImplementation implements LearningExperiencePersistence {
    private final SimpleProblemActivityLearningExperienceRepository simpleProblemActivityLearningExperienceRepository;
    private final ScenarioProblemSolvingActivityLearningExperienceRepository scenarioProblemSolvingActivityLearningExperienceRepository;
    private final StoryBasedActivityLearningExperienceRepository storyBasedActivityLearningExperienceRepository;
    private final OnlineDiscussionSimulationActivityLearningExperienceRepository onlineDiscussionSimulationActivityLearningExperienceRepository;

    //TODO: extract superclass for learning result & experience
    protected Set<? extends JpaRepository<? extends LearningExperience<?>, LearningExperienceId>> getRepositories() {
        return Arrays.stream(this.getClass().getDeclaredFields()) // Get all fields in this class
                .filter(field -> JpaRepository.class.isAssignableFrom(field.getType())) // Keep only JpaRepository fields
                .map(field -> {
                    try {
                        field.setAccessible(true); // Allow access to private fields
                        return (JpaRepository<? extends LearningExperience<?>, LearningExperienceId>) field.get(this); // Get field value
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to access repository field: " + field.getName(), e);
                    }
                })
                .collect(Collectors.toSet()); // Collect into a Set
    }

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
