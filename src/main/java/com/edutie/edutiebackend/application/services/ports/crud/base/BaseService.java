package com.edutie.edutiebackend.application.services.ports.crud.base;

import java.util.Optional;
import java.util.Set;

/**
 * Base service interface providing crud activities.
 * @param <T>
 * @param <TInnerId>
 */
public interface BaseService<T, TInnerId> {
    Optional<T> getById(TInnerId id);
    Set<T> getAll();
    Optional<T> overwrite(TInnerId id, T entity);
    boolean delete(TInnerId id);
}
