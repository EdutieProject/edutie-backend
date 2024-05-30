package com.edutie.backend.domain.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import validation.Result;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class NavigableEntityBase<TNavigationEntity extends NavigableEntityBase<TNavigationEntity, TId>, TId extends Serializable> extends AuditableEntityBase<TId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_element_id", nullable = true)
    @JsonIgnore
    protected TNavigationEntity previousElement = null;

    @OneToMany(mappedBy = "previousElement", fetch = FetchType.LAZY)
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
