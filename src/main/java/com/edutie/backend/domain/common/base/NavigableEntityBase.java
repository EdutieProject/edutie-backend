package com.edutie.backend.domain.common.base;

import com.edutie.backend.api.serialization.IdOnlySerializer;
import com.edutie.backend.api.serialization.IdOnlyCollectionSerializer;
import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import validation.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class NavigableEntityBase<TNavigationEntity extends NavigableEntityBase<TNavigationEntity, TId>, TId extends Serializable> extends AuditableEntityBase<TId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_element_id", nullable = true)
    @JsonSerialize(using = IdOnlySerializer.class)
    @Setter
    private TNavigationEntity previousElement = null;

    @OneToMany(mappedBy = "previousElement", fetch = FetchType.LAZY)
    @JsonSerialize(using = IdOnlyCollectionSerializer.class)
    protected final Set<TNavigationEntity> nextElements = new HashSet<>();

    public abstract Result addNextElement(TNavigationEntity navigationEntity);

    public Result removeNextElement(TNavigationEntity navigationEntity) {
        return nextElements.remove(navigationEntity) ?
                Result.success() : Result.failure(NavigationErrors.elementNotFound(this.getClass()));
    }

    //TODO: no Result as remove value
    public Result removeNextElementById(TId entityId) {
        var searchedEntity = nextElements.stream().filter(o -> o.getId() == entityId).findFirst();
        return searchedEntity.isPresent() ?
                removeNextElement(searchedEntity.get()) : Result.failure(NavigationErrors.elementNotFound(this.getClass()));
    }
}
