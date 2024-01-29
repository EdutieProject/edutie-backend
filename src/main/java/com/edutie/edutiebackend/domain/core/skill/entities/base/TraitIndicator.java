package com.edutie.edutiebackend.domain.core.skill.entities.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.common.enums.PersistableEnum;
import com.edutie.edutiebackend.domain.core.skill.identities.IndicatorId;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class TraitIndicator<TTrait extends Enum<TTrait>> extends EntityBase<IndicatorId> {
    TTrait trait;
    int value;
}
