package com.edutie.edutiebackend.domain.core.skill.entities;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.skill.entities.base.TraitIndicator;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
public class IntelligenceIndicator extends TraitIndicator<Intelligence> {
}
