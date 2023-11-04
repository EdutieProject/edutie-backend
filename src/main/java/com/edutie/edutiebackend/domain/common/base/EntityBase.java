package com.edutie.edutiebackend.domain.common.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Base class for an entity.
 * @param <TId> Type of id. Example: Book entity has an id of type BookId
 */
@Getter
@Setter
@MappedSuperclass
public abstract class EntityBase<TId extends Serializable> {
    @Id
    private TId id;

    /**
     * Checks whether entity is same as the provided other.
     * Same in terms of entities means their Ids are of same type (!)
     * and value. This comparison does not check fields values
     * equalities.
     * @param object entity to compare
     * @return true if entities are same, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EntityBase<?> that = (EntityBase<?>) object;

        // Check if the ids are of the same type or at least one of them is null
        var differentIdType = id != null
                ? !id.getClass().equals(that.id.getClass())
                : that.id != null;

        if (differentIdType) return false;

        // Check if the ids are equal
        return id != null && id.equals(that.id);
    }

    /**
     * Gets hash code of entity's id
     * @return entity's id hashcode
     */
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
