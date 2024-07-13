package com.edutie.backend.domain.personalization.knowledgecorrelation;

import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KnowledgeCorrelation {
    private KnowledgeSubjectId knowledgeSubjectId;
    private int correlationFactor;

    public KnowledgeCorrelation(KnowledgeSubjectId knowledgeSubjectId, int correlationFactor) {
        this.correlationFactor = correlationFactor;
        this.knowledgeSubjectId = knowledgeSubjectId;
    }
}
