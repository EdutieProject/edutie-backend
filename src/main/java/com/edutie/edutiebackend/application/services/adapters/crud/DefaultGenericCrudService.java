package com.edutie.edutiebackend.application.services.adapters.crud;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;

import java.util.Optional;
import java.util.Set;

public class DefaultGenericCrudService<T, TId>  implements GenericCrudService<T, TId> {
    /**
     * @param id
     * @return
     */
    @Override
    public Optional<T> getById(TId id) {
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public Set<T> getAll() {
        return null;
    }

    /**
     * @param id
     * @param entity
     * @return
     */
    @Override
    public Optional<T> overwrite(TId id, T entity) {
        return Optional.empty();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(TId id) {
        return false;
    }
}
