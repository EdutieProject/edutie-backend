package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.skill.entities.AbilityIndicator;
import com.edutie.backend.domain.core.skill.identities.IndicatorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityIndicatorRepository extends JpaRepository<AbilityIndicator, IndicatorId> {
}
