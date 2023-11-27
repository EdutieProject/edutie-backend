package com.edutie.edutiebackend.application.services.ports.crud;

import java.util.Optional;
import java.util.Set;

public interface GenericCrudService<T, TId> {
    Optional<T> getById(TId id);
    Set<T> getAll();
    Optional<T> overwrite(TId id, T entity);
    boolean delete(TId id);
}
