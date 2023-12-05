package com.edutie.edutiebackend.application.services.common.servicebase;

import java.util.Optional;

/**
 * Base service interface providing crud activities.
 * @param <TEntity>
 * @param <TInnerId>
 */
public interface GenericCrudService<TEntity, TInnerId> extends GenericRetrievalService<TEntity, TInnerId> {
    TEntity create(TEntity entity);
    Optional<TEntity> overwrite(TEntity entity);
    boolean delete(TInnerId id);
}
