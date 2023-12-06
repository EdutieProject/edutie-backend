package com.edutie.edutiebackend.application.services.common.servicebase;

import java.util.Optional;
import java.util.Set;

public interface GenericRetrievalService<TEntity, TInnerId> {
    Optional<TEntity> getById(TInnerId id);
    Set<TEntity> getAll();
}
