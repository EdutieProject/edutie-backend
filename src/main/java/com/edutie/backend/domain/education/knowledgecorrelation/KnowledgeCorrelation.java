package com.edutie.backend.domain.education.knowledgecorrelation;

import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import lombok.*;

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
