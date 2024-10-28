package com.edutie.backend.domain.common.base;

import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Base class for an entity.
 *
 * @param <TId> Type of id. Must be strongly typed using Identifier class.
 * @see Identifier Identifier class
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class EntityBase<TId extends Identifier<?> & Serializable> {
    @EmbeddedId
    @AttributeOverride(name = "identifierValue", column = @Column(name = "id"))
    @EqualsAndHashCode.Include
    private TId id;
}
