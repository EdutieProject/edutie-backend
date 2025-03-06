package com.edutie.infrastructure.knowledgemap.knowledgesubject;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.infrastructure.knowledgemap.common.KnowledgeMapService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.KnowledgeSubjectSearchSchema;
import validation.WrapperResult;

import java.util.List;

public interface KnowledgeSubjectSearchService extends KnowledgeMapService {
    WrapperResult<List<KnowledgeSubject>> search(KnowledgeSubjectSearchSchema schema);
}
