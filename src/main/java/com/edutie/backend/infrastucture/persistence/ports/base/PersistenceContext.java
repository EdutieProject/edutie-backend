package com.edutie.backend.infrastucture.persistence.ports.base;

import com.edutie.backend.domain.common.base.EntityBase;
import validation.Result;

import java.io.Serializable;
import java.util.Optional;


public interface PersistenceContext<T extends EntityBase<TId>, TId extends Serializable> {
    Optional<T> getById(TId id);
    Result save();
    Result deleteById(TId id);
}
