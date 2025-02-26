package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import org.springframework.data.jpa.repository.*;

public interface ElementalRequirementRepository extends JpaRepository<ElementalRequirement, ElementalRequirementId> { }
