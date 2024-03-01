package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.education.skill.entities.IntelligenceIndicator;
import com.edutie.backend.domain.education.skill.identities.IndicatorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntelligenceIndicatorRepository extends JpaRepository<IntelligenceIndicator, IndicatorId> {
}
