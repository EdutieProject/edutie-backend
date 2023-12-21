package com.edutie.edutiebackend.application.services.common.servicebase;

import java.util.Optional;

/**
 * Base service interface providing crud activities.
 * @param <TEntity>
 * @param <TInnerId>
 */
public interface GenericCrudService<TEntity, TInnerId> extends GenericRetrievalService<TEntity, TInnerId> {
    /**
     * @param entity
     * @return
     */
    TEntity create(TEntity entity);
    /**
     * @param entity
     * @return
     */
    Optional<TEntity> overwrite(TEntity entity);
    /**
     * @param id
     * @return
     */
    boolean delete(TInnerId id);
}
