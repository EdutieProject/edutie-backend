package com.edutie.backend.domain.common.persistence;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.base.identity.Identifier;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.common.RoleRepository;
import validation.Result;

import java.util.NoSuchElementException;

/**
 * Base Persistence extension providing additional operations for Role profile support.
 *
 * @param <TEntity> Role entity type
 * @param <TId>     Role entity identifier type
 */
public interface RolePersistence<TEntity extends Role<TId>, TId extends Identifier<?>> extends Persistence<TEntity, TId> {

	RoleRepository<TEntity, TId> getRepository();

	/**
	 * Retrieves role profile for the given user. The presence of the role profile should be checked
	 * using authorization before using this function.
	 *
	 * @param userId id of a user
	 * @return Role profile of a user
	 */
	default TEntity getByAuthorizedUserId(UserId userId) {
		return getRepository().findByOwnerUserId(userId).isPresent() ? getRepository().findByOwnerUserId(userId).get() : null;
	}

	/**
	 * Removes the role for the user of specified id from the database. Success is returned even
	 * when the role is not present at first in the database. Failure is returned only when something goes
	 * wrong
	 *
	 * @param userId entity id
	 * @return Result object
	 */
	default Result removeForUser(UserId userId) {
		try {
			assert getRepository().findByOwnerUserId(userId).isPresent();
			TEntity educator = getRepository().findByOwnerUserId(userId).get();
			getRepository().delete(educator);
			return Result.success();
		} catch (NoSuchElementException ignored) {
			return Result.failure(PersistenceError.notFound(Educator.class));
		} catch (Exception exception) {
			return Result.failure(PersistenceError.exceptionEncountered(exception));
		}
	}
}
