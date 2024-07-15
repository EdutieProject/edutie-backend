package com.edutie.backend.domain.common.persistence;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.base.identity.Identifier;
import com.edutie.backend.infrastucture.persistence.persistence.common.PersistenceError;
import org.springframework.data.jpa.repository.JpaRepository;
import validation.Result;
import validation.WrapperResult;

import java.util.Optional;


public interface Persistence<T extends EntityBase<TId>, TId extends Identifier<?>> {
    /**
     * Override this to provide repository for default methods
     * @return crud jpa repository
     */
    JpaRepository<T, TId> getRepository();

    /**
     * Override this to provide entity class for default methods
     * @return class of persistence entity
     */
    Class<T> entityClass();


    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param id entity id
     * @return Optional entity
     */
    default WrapperResult<T> getById(TId id) {
        try {
            Optional<T> fetchedEntity = getRepository().findById(id);
            return fetchedEntity.map(WrapperResult::successWrapper)
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(entityClass())));
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
    default Result save(T entity) {
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
    default Result removeById(TId id) {
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
    default Result remove(T entity) {
        return removeById(entity.getId());
    }
}
