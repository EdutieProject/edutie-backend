package com.edutie.domain.core.common.persistence;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.base.identity.Identifier;
import validation.Result;
import validation.WrapperResult;

/**
 * Base persistence. By default, it uses jpa repositories to perform basic operations on entities.
 * Override its methods to use different kinds of techniques to perform DB operations.
 *
 * @param <T>   Type of entity to be persisted
 * @param <TId> Identifier of the entity type
 */
public interface Persistence<T extends EntityBase<TId>, TId extends Identifier<?>> {

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param id entity id
     * @return Optional entity
     */
    WrapperResult<T> getById(TId id);

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @return Result object
     */
    Result save(T entity);

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param id entity id
     * @return Result object
     */
    Result removeById(TId id);

    /**
     * Removes the following entity from the database. This is a default method, which when not
     * override is correlated with removeById() method.
     *
     * @param entity entity
     * @return Result
     */
    Result remove(T entity);
}
