package com.edutie.infrastructure.persistence.base;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.base.identity.Identifier;
import com.edutie.domain.core.common.persistence.Persistence;
import com.edutie.infrastructure.persistence.PersistenceError;
import org.springframework.data.jpa.repository.JpaRepository;
import validation.Result;
import validation.WrapperResult;

import java.util.Optional;

public abstract class DefaultPersistence<T extends EntityBase<TId>, TId extends Identifier<?>> implements Persistence<T, TId> {
    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    protected abstract JpaRepository<T, TId> getRepository();

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    protected abstract Class<T> entityClass();

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param id entity id
     * @return Optional entity
     */
    public WrapperResult<T> getById(TId id) {
        try {
            Optional<T> fetchedEntity = getRepository().findById(id);
            return fetchedEntity.map(WrapperResult::successWrapper).orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(entityClass())));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @return Result object
     */
    public Result save(T entity) {
        try {
            getRepository().saveAndFlush(entity);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param id entity id
     * @return Result object
     */
    public Result removeById(TId id) {
        try {
            getRepository().deleteById(id);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the following entity from the database. This is a default method, which when not
     * override is correlated with removeById() method.
     *
     * @param entity entity
     * @return Result
     */
    public Result remove(T entity) {
        return removeById(entity.getId());
    }
}
