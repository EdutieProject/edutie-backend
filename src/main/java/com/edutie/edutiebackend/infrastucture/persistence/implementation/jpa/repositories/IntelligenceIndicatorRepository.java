package com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.edutiebackend.domain.core.skill.entities.IntelligenceIndicator;
import com.edutie.edutiebackend.domain.core.skill.identities.IndicatorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntelligenceIndicatorRepository extends JpaRepository<IntelligenceIndicator, IndicatorId> {
}
