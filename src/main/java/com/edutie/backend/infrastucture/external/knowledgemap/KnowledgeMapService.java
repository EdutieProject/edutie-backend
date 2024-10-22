package com.edutie.backend.infrastucture.external.knowledgemap;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import validation.WrapperResult;

import java.util.Set;

/**
 * Service for interaction with externally implemented Knowledge Map.
 */
public interface KnowledgeMapService {
	WrapperResult<Set<KnowledgeCorrelation>> getKnowledgeCorrelations(Set<KnowledgeSubjectId> knowledgeSubjectIds);
}
