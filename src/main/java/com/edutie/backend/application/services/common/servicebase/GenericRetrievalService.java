package com.edutie.backend.application.services.common.servicebase;

import java.util.Optional;
import java.util.Set;

public interface GenericRetrievalService<TEntity, TInnerId> {
    /**
     * @param id
     * @return
     */
    Optional<TEntity> getById(TInnerId id);
    /**
     * @return
     */
    Set<TEntity> getAll();
}
