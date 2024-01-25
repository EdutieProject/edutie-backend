package com.edutie.edutiebackend.domain.core.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public class NavigableEntityBase<TNavigationEntity extends NavigableEntityBase<?, ?>, TId extends Serializable> extends AuditableEntityBase<TId> {

    @MapsId("id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "previous_element_id")
    @Nullable
    @JsonIgnore
    @Getter
    @Setter
    private TNavigationEntity previousElement = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "previousElement")
    @JoinColumn(name = "previous_element_id")
    private final Set<TNavigationEntity> nextElements = new HashSet<>();

    public void addNextElement(TNavigationEntity navigationEntity) {
        nextElements.add(navigationEntity);
    }

    public void removeNextElement(TNavigationEntity navigationEntity) {
        nextElements.remove(navigationEntity);
    }

    public void removeNextElementById(TId entityId) {
        var searchedEntity = nextElements.stream().filter(o->o.getId()==entityId).findFirst();
        searchedEntity.ifPresent(nextElements::remove);
    }
}
