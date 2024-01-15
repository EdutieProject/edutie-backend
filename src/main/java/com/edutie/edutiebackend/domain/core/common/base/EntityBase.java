package com.edutie.edutiebackend.domain.core.common.base;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * Base class for an entity.
 * @param <TId> Type of id. Example: Book entity has an id of type BookId
 */
@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
public abstract class EntityBase<TId extends Serializable> {
    @EmbeddedId
    @AttributeOverride(name = "identifierValue", column = @Column(name = "id"))
    private TId entityId;
}
