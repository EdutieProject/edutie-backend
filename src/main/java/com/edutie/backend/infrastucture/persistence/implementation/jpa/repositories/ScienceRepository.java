package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScienceRepository extends JpaRepository<Science, ScienceId> {
}
