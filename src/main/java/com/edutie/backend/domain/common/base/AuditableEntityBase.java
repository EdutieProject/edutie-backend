package com.edutie.backend.domain.common.base;

import java.io.Serializable;
import java.time.LocalDate;

import com.edutie.backend.domain.common.identities.UserId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "created_on", nullable = false)
    private LocalDate createdOn = LocalDate.now();
    private LocalDate updatedOn;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "update_user_id"))
    private UserId updatedBy;
    @Setter(AccessLevel.PROTECTED)
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "create_user_id", nullable = false))
    private UserId createdBy;
}
