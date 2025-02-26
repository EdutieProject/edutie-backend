package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.domain.core.education.learningrequirement.identities.ElementalRequirementId;
import org.springframework.data.jpa.repository.*;

public interface ElementalRequirementRepository extends JpaRepository<ElementalRequirement, ElementalRequirementId> { }
