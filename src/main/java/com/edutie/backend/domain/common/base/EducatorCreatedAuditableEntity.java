package com.edutie.backend.domain.common.base;

import com.edutie.backend.domain.common.base.identity.Identifier;
import com.edutie.backend.domain.education.educator.Educator;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Auditable entity with {@code authorEducator} field and relationship.
 *
 * @param <TId>
 */
@Getter
@MappedSuperclass
public abstract class EducatorCreatedAuditableEntity<TId extends Identifier<?>> extends AuditableEntityBase<TId> {
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PROTECTED)
    private Educator authorEducator;
}
