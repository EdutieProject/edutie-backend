package com.edutie.backend.domain.core.skill.entities.base;

import com.edutie.backend.domain.core.skill.identities.IndicatorId;
import com.edutie.backend.domain.core.common.base.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@MappedSuperclass
public abstract class TraitIndicator<TTrait extends Enum<TTrait>> extends EntityBase<IndicatorId> {
    @Column(name = "indicator_value")
    int value;

    public abstract TTrait getTrait();
    public abstract void setTrait(TTrait trait);
}
