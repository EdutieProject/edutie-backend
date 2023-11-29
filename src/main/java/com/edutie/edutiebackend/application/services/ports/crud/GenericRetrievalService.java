package com.edutie.edutiebackend.application.services.ports.crud;

import java.util.Optional;
import java.util.Set;

public interface GenericRetrievalService<TEntity, TInnerId> {
    Optional<TEntity> getById(TInnerId id);
    Set<TEntity> getAll();
}
