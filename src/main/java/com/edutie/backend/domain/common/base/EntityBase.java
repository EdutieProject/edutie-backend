package com.edutie.backend.domain.common.base;

import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.*;
import lombok.*;

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
public abstract class EntityBase<TId extends Identifier<?> & Serializable> {
	@EmbeddedId
	@AttributeOverride(name = "identifierValue", column = @Column(name = "id"))
	private TId id;
}
