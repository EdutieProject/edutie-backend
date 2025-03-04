package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.infrastructure.persistence.PersistenceError;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class LearningExperiencePersistenceImplementation implements LearningExperiencePersistence {
    private final LearningExperienceRepository learningExperienceRepository;
    private final SimpleProblemActivityLearningExperienceRepository simpleProblemActivityLearningExperienceRepository;
    private final ScenarioProblemSolvingActivityLearningExperienceRepository scenarioProblemSolvingActivityLearningExperienceRepository;
    private final StoryBasedActivityLearningExperienceRepository storyBasedActivityLearningExperienceRepository;
    private final OnlineDiscussionSimulationActivityLearningExperienceRepository onlineDiscussionSimulationActivityLearningExperienceRepository;


    //TODO: extract superclass for learning result & experience
    protected Set<? extends JpaRepository<? extends LearningExperience<?>, LearningExperienceId>> getRepositories() {
        return Arrays.stream(this.getClass().getDeclaredFields()) // Get all fields in this class
                .filter(field -> JpaRepository.class.isAssignableFrom(field.getType()) && !LearningExperienceRepository.class.isAssignableFrom(field.getType())) // Keep only JpaRepository fields
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
        try {
            for (JpaRepository<? extends LearningExperience<?>, LearningExperienceId> repository : this.getRepositories()) {
                Optional<? extends LearningExperience<?>> optionalLearningExperience = repository.findById(learningExperienceId);
                if (optionalLearningExperience.isPresent()) {
                    return WrapperResult.successWrapper(optionalLearningExperience.get());
                }
            }
            return WrapperResult.failureWrapper(PersistenceError.notFound(SimpleProblemActivityLearningExperience.class));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
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
    public Result save(LearningExperience<?> entity) {
        try {
            learningExperienceRepository.save(entity);
            return Result.success();
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
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
        try {
            for (JpaRepository<? extends LearningExperience<?>, LearningExperienceId> repository : this.getRepositories()) {
                repository.deleteById(learningExperienceId);
            }
            return Result.success();
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
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
        return removeById(entity.getId());
    }

}
