package com.edutie.backend.infrastucture.knowledgemap;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import validation.WrapperResult;

import java.util.List;

/**
 * Service for interaction with externally implemented Knowledge Map.
 */
public interface KnowledgeMapService {
    WrapperResult<List<KnowledgeCorrelation>> getKnowledgeCorrelations(KnowledgeSubjectId knowledgeSubjectId);
}
