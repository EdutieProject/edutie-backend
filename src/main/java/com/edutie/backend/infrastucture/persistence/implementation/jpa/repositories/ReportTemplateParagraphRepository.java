package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.education.exercisetype.entities.ReportTemplateParagraph;
import com.edutie.backend.domain.education.exercisetype.identities.ReportTemplateParagraphId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportTemplateParagraphRepository extends JpaRepository<ReportTemplateParagraph, ReportTemplateParagraphId> {
}
