package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.learningrequirement.entities.SubRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.SubRequirementId;
import org.springframework.data.jpa.repository.*;

public interface SubRequirementRepository extends JpaRepository<SubRequirement, SubRequirementId> { }
