package com.edutie.backend.infrastructure.persistence.jpa.repositories;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import org.springframework.data.jpa.repository.*;

public interface ScienceRepository extends JpaRepository<Science, ScienceId> { }
