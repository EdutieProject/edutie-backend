package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Base class for a role profile
 *
 * @param <TId>
 */
@Setter(AccessLevel.PROTECTED)
@Getter
@MappedSuperclass
public abstract class Role<TId extends Identifier<?>> extends EntityBase<TId> {
	protected final LocalDateTime assignedOn = LocalDateTime.now();
	@Embedded
	@AttributeOverride(name = "identifierValue", column = @Column(name = "user_id"))
	protected UserId ownerUserId;
}
