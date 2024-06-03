package com.edutie.backend.domain.common.base;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Base class for an entity. Shipped with update and creation related fields
 *
 * @param <TId> Type of id. Example: Book entity has an id of type BookId
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class AuditableEntityBase<TId extends Identifier<?>> extends EntityBase<TId> {
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime updatedOn;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "update_user_id"))
    private UserId updatedBy;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "create_user_id", nullable = false))
    private UserId createdBy;

    /**
     * Adjusts the update-related fields of the auditable entity.
     *
     * @param userId user performing the modification
     */
    public void update(UserId userId) {
        updatedBy = userId;
        updatedOn = LocalDateTime.now();
    }
}
