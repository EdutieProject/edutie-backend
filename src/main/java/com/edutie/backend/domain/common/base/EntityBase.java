package com.edutie.backend.domain.common.base;

import java.io.Serializable;

import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * Base class for an entity.
 * @param <TId> Type of id. Example: Book entity has an id of type BookId
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EqualsAndHashCode
public abstract class EntityBase<TId extends Identifier<?> & Serializable> {
    @EmbeddedId
    @AttributeOverride(name = "identifierValue", column = @Column(name = "id"))
    private TId id;
}
