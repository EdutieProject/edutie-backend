package com.edutie.backend.infrastructure.external.knowledgemap;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.infrastructure.external.common.ExternalService;
import validation.WrapperResult;

import java.util.Set;

/**
 * Service for interaction with externally implemented Knowledge Map.
 */
public interface KnowledgeMapService extends ExternalService {
	WrapperResult<Set<KnowledgeCorrelation>> getKnowledgeCorrelations(Set<KnowledgeSubjectId> knowledgeSubjectIds);
}
