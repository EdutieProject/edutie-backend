package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * Restricted role is a base class for a user profile that needs to be assigned by an admin
 *
 * @param <TId>
 */
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class RestrictedRole<TId extends Identifier<?>> extends Role<TId> {
    @ManyToOne(targetEntity = Administrator.class, fetch = FetchType.EAGER)
    protected Administrator assignedBy;
}
