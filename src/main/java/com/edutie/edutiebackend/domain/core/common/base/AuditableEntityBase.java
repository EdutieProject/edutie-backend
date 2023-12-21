package com.edutie.edutiebackend.domain.core.common.base;

import java.io.Serializable;
import java.time.LocalDate;

import com.edutie.edutiebackend.domain.core.common.identities.UserId;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


/**
 * Base class for an entity. Shipped with update and creation related fields
 * @param <TId> Type of id. Example: Book entity has an id of type BookId
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntityBase<TId extends Serializable> extends EntityBase<TId>{
    private LocalDate createdOn = LocalDate.now();
    private LocalDate updatedOn = LocalDate.now(); //UWAGA: Tutaj jest wyjątkowa sytuacja, że zmienione=utworzone w jednej sytuacji
    private UserId updatedBy;
    private UserId createdBy;
}
