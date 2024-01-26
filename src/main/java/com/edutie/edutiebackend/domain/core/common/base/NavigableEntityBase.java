package com.edutie.edutiebackend.domain.core.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@MappedSuperclass
public abstract class NavigableEntityBase<TNavigationEntity extends NavigableEntityBase<?, ?>, TId extends Serializable> extends AuditableEntityBase<TId> {

//    @MapsId("id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "previous_element_id", nullable = true)
    @JsonIgnore
    @Setter
    private TNavigationEntity previousElement = null;

    @OneToMany(mappedBy = "previousElement", fetch = FetchType.EAGER)
    protected final Set<TNavigationEntity> nextElements = new HashSet<>();

    public abstract void addNextElement(TNavigationEntity navigationEntity);

    public void removeNextElement(TNavigationEntity navigationEntity) {
        nextElements.remove(navigationEntity);
    }

    public void removeNextElementById(TId entityId) {
        var searchedEntity = nextElements.stream().filter(o->o.getId()==entityId).findFirst();
        searchedEntity.ifPresent(nextElements::remove);
    }
}
