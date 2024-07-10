package com.edutie.backend.domain.personalization.knowledgecorrelation;

import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class KnowledgeCorrelation {
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "knowledge_subject_id"))
    private KnowledgeSubjectId knowledgeSubjectId;
    private int correlationFactor;
}
