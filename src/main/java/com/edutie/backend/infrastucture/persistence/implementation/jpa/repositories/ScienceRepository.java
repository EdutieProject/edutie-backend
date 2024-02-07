package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.science.Science;
import com.edutie.backend.domain.core.science.identities.ScienceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScienceRepository extends JpaRepository<Science, ScienceId> {
}
