package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.ElementalRequirementId;
import org.springframework.data.jpa.repository.*;

public interface ElementalRequirementRepository extends JpaRepository<ElementalRequirement, ElementalRequirementId> { }
