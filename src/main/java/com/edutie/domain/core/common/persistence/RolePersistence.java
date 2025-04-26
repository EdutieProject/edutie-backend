package com.edutie.domain.core.common.persistence;

import com.edutie.domain.core.administration.Role;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.base.identity.Identifier;
import validation.Result;

/**
 * Base Persistence extension providing additional operations for Role profile support.
 *
 * @param <TEntity> Role entity type
 * @param <TId>     Role entity identifier type
 */
public interface RolePersistence<TEntity extends Role<TId>, TId extends Identifier<?>> extends Persistence<TEntity, TId> {

    /**
     * Retrieves role profile for the given user. The presence of the role profile should be checked
     * using authorization before using this function.
     *
     * @param userId id of a user
     * @return Role profile of a user
     */
    TEntity getByAuthorizedUserId(UserId userId);

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
