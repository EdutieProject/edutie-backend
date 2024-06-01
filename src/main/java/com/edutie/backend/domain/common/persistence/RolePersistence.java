package com.edutie.backend.domain.common.persistence;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.base.identity.Identifier;
import validation.Result;

public interface RolePersistence<TEntity extends EntityBase<TId>, TId extends Identifier<?>> extends Persistence<TEntity, TId> {
    /**
     * Retrieves role profile for the given user. The presence of the role profile should be checked
     * using authorization before using this function.
     *
     * @param userId id of a user
     * @return Role profile of a user
     */
    TEntity getByUserId(UserId userId);

    /**
     * Persists the provided role profile into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @return Result object
     */
    Result save(TEntity entity);

    /**
     * Removes the role for the user of specified id from the database. Success is returned even
     * when the role is not present at first in the database. Failure is returned only when something goes
     * wrong
     *
     * @param userId entity id
     * @return Result object
     */
    Result removeForUser(UserId userId);
}
