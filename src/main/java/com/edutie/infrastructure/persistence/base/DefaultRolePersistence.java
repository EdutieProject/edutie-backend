package com.edutie.infrastructure.persistence.base;

import com.edutie.domain.core.administration.Role;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.base.identity.Identifier;
import com.edutie.domain.core.common.persistence.RolePersistence;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.infrastructure.authorization.AuthorizationException;
import com.edutie.infrastructure.persistence.PersistenceError;
import com.edutie.infrastructure.persistence.implementation.common.repositories.RoleRepository;
import validation.Result;

import java.util.NoSuchElementException;

public abstract class DefaultRolePersistence<T extends Role<TId>, TId extends Identifier<?>>
        extends DefaultPersistence<T, TId> implements RolePersistence<T, TId> {
    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    protected abstract RoleRepository<T, TId> getRepository();

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    protected abstract Class<T> entityClass();


    public T getByAuthorizedUserId(UserId userId) {
        try {
            return getRepository().findByOwnerUserId(userId).get();
        } catch (NoSuchElementException unused) {
            throw new AuthorizationException(this.getClass());
        }
    }

    public Result removeForUser(UserId userId) {
        try {
            assert getRepository().findByOwnerUserId(userId).isPresent();
            T educator = getRepository().findByOwnerUserId(userId).get();
            getRepository().delete(educator);
            return Result.success();
        } catch (NoSuchElementException ignored) {
            return Result.failure(PersistenceError.notFound(Educator.class));
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

}
