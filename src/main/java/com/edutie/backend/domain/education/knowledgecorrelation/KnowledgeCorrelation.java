package com.edutie.backend.domain.education.knowledgecorrelation;

import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

/**
 * Knowledge correlation represents knowledge subject and its relation importance as correlation factor
 */
@Getter
@Setter
public class KnowledgeCorrelation {
	private KnowledgeSubjectId knowledgeSubjectId;
	private int correlationFactor;

	public KnowledgeCorrelation(KnowledgeSubjectId knowledgeSubjectId, int correlationFactor) {
		this.knowledgeSubjectId = knowledgeSubjectId;
		this.correlationFactor = correlationFactor;
	}
}
