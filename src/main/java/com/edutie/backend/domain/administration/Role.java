package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.common.base.EntityBase;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Base class for a role
 * @param <TId>
 */
@Setter(AccessLevel.PROTECTED)
@Getter
@MappedSuperclass
public abstract class Role<TId extends Serializable> extends EntityBase<TId> {
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "user_id"))
    protected UserId ownerUserId;
}
