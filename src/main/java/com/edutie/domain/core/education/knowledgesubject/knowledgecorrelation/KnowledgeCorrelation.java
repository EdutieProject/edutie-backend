package com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation;

import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import lombok.Getter;
import lombok.Setter;

/**
 * Knowledge correlation represents knowledge subject and its relation importance as correlation factor
 */
@Getter
@Setter
public class KnowledgeCorrelation {
    private KnowledgeSubjectId sourceSubjectId;
    private KnowledgeSubjectId correlatedSubjectId;
    private int correlationFactor;

    public KnowledgeCorrelation(KnowledgeSubjectId sourceSubjectId, KnowledgeSubjectId correlatedSubjectId, int correlationFactor) {
        this.sourceSubjectId = sourceSubjectId;
        this.correlatedSubjectId = correlatedSubjectId;
        this.correlationFactor = correlationFactor;
    }
}
