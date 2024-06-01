package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class RestrictedRole<TId extends Identifier<?>> extends Role<TId> {
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "assigned_by_admin_id", nullable = false))
    protected AdminId assignedBy;
}
