package com.edutie.edutiebackend.domain.core.skill.entities.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.common.enums.PersistableEnum;
import com.edutie.edutiebackend.domain.core.skill.identities.IndicatorId;
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
