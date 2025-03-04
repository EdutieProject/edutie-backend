package com.edutie.domain.core.administration;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.base.identity.Identifier;
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
 * Base class for a role profile
 *
 * @param <TId>
 */
@Setter(AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Role<TId extends Identifier<?>> extends EntityBase<TId> {
    protected final LocalDateTime assignedOn = LocalDateTime.now();
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "user_id"))
    protected UserId ownerUserId;
}
