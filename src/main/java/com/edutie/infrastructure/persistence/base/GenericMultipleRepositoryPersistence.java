package com.edutie.infrastructure.persistence.base;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.base.identity.Identifier;
import com.edutie.domain.core.common.persistence.Persistence;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.infrastructure.persistence.PersistenceError;
import org.springframework.data.jpa.repository.JpaRepository;
import validation.Result;
import validation.WrapperResult;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GenericMultipleRepositoryPersistence<T extends EntityBase<TId>, TId extends Identifier<?>> implements Persistence<T, TId> {
    protected abstract Class<? extends JpaRepository<?, ?>>[] getOmittedRepositoryClasses();

    protected abstract JpaRepository<T, TId> getPolymorphicRepository();

    protected Set<? extends JpaRepository<? extends T, TId>> getRepositories() {
        return Arrays.stream(this.getClass().getDeclaredFields()) // Get all fields in this class
                .filter(field -> JpaRepository.class.isAssignableFrom(field.getType())
                        && Arrays.stream(getOmittedRepositoryClasses()).noneMatch(o -> o.isAssignableFrom(field.getType()))
                ).map(field -> {
                    try {
                        field.setAccessible(true); // Allow access to private fields
                        return (JpaRepository<? extends T, TId>) field.get(this); // Get field value
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
     * @param id entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<T> getById(TId id) {
        try {
            for (JpaRepository<? extends T, TId> repository : this.getRepositories()) {
                Optional<? extends T> optionalEntity = repository.findById(id);
                if (optionalEntity.isPresent()) {
                    return WrapperResult.successWrapper(optionalEntity.get());
                }
            } //TODO: FIX that shit!
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
    public Result save(T entity) {
        try {
            getPolymorphicRepository().save(entity);
            return Result.success();
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param id entity id
     * @return Result object
     */
    @Override
    public Result removeById(TId id) {
        try {
            for (JpaRepository<? extends T, TId> repository : this.getRepositories()) {
                repository.deleteById(id);
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
    public Result remove(T entity) {
        return removeById(entity.getId());
    }
}
