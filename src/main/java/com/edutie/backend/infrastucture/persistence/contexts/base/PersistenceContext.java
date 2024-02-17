package com.edutie.backend.infrastucture.persistence.contexts.base;

import com.edutie.backend.domain.common.base.EntityBase;
import validation.Result;

import java.io.Serializable;
import java.util.Optional;


public interface PersistenceContext<T extends EntityBase<TId>, TId extends Serializable> {
    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     * @param id entity id
     * @return Optional entity
     */
    Optional<T> getById(TId id);

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     * @return Result object
     */
    Result save(T entity);

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     * @param id entity id
     * @return Result object
     */
    Result deleteById(TId id);
}
