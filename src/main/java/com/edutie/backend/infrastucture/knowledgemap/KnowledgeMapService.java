package com.edutie.backend.infrastucture.knowledgemap;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import validation.WrapperResult;

import java.util.List;

public interface KnowledgeMapService {
    WrapperResult<List<KnowledgeCorrelation>> getKnowledgeCorrelations(KnowledgeSubjectId knowledgeSubjectId);
}
