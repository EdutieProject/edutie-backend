package com.edutie.infrastructure.knowledgemap.knowledgesubject;

import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import com.edutie.infrastructure.knowledgemap.common.KnowledgeMapService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeSubjectDetailsSchema;
import validation.WrapperResult;

public interface GetKnowledgeSubjectDetailsService extends KnowledgeMapService {
    WrapperResult<KnowledgeSubjectDetailsView> getDetails(GetKnowledgeSubjectDetailsSchema schema);
}
