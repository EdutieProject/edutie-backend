package com.edutie.backend.domain.studyprogram.common;

import com.edutie.backend.api.serialization.serializers.IdOnlyCollectionSerializer;
import com.edutie.backend.api.serialization.serializers.IdOnlySerializer;
import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.base.identity.Identifier;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import validation.Result;

import java.util.HashSet;
import java.util.Set;

/**
 * Tree element base entity. Subtypes are tree-like study program structure elements.
 *
 * @param <TNavigationEntity> Type of the entity creating the tree
 * @param <TId>               Type of entity identifier
 */
//TODO: move to study program subdomain
@Getter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class TreeElementEntityBase<TNavigationEntity extends TreeElementEntityBase<TNavigationEntity, TId>, TId extends Identifier<?>>
        extends EducatorCreatedAuditableEntity<TId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_element_id", nullable = true)
    @JsonSerialize(using = IdOnlySerializer.class)
    protected TNavigationEntity previousElement = null;

    @OneToMany(mappedBy = "previousElement", fetch = FetchType.LAZY)
    @JsonSerialize(using = IdOnlyCollectionSerializer.class)
    protected final Set<TNavigationEntity> nextElements = new HashSet<>();

    public abstract Result addNextElement(TNavigationEntity navigationEntity);

    public abstract Result setPreviousElement(TNavigationEntity navigationEntity);

    public void removeNextElement(TNavigationEntity navigationEntity) {
        nextElements.remove(navigationEntity);
    }

    public void removeNextElementById(TId entityId) {
        nextElements.removeIf(o -> o.getId().equals(entityId));
    }
}
