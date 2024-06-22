package com.edutie.backend.domain.education.exercisetype.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.exercisetype.identities.ReportTemplateParagraphId;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ReportTemplateParagraph extends EntityBase<ReportTemplateParagraphId> {
    private String title;
    private String text;
    private short ordinal;
}
