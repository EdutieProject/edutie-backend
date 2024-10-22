package com.edutie.backend.infrastructure.persistence.jpa.repositories;

import com.edutie.backend.domain.education.exercisetype.entities.ReportTemplateParagraph;
import com.edutie.backend.domain.education.exercisetype.identities.ReportTemplateParagraphId;
import org.springframework.data.jpa.repository.*;

public interface ReportTemplateParagraphRepository extends JpaRepository<ReportTemplateParagraph, ReportTemplateParagraphId> { }
