package com.edutie.backend.domain.common.base;

import com.edutie.backend.domain.common.errors.NavigationErrors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import validation.Result;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@MappedSuperclass
public abstract class NavigableEntityBase<TNavigationEntity extends NavigableEntityBase<TNavigationEntity, TId>, TId extends Serializable> extends AuditableEntityBase<TId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_element_id", nullable = true)
    @JsonIgnore
    @Setter
    //TODO: parent element validation
    private TNavigationEntity previousElement = null;

    @OneToMany(mappedBy = "previousElement", fetch = FetchType.LAZY)
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
