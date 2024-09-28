package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.*;
import lombok.*;

/**
 * Restricted role is a base class for a user profile that needs to be assigned by an admin
 *
 * @param <TId>
 */
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class RestrictedRole<TId extends Identifier<?>> extends Role<TId> {
	@ManyToOne(targetEntity = Administrator.class, fetch = FetchType.EAGER)
	protected Administrator assignedBy;
}
