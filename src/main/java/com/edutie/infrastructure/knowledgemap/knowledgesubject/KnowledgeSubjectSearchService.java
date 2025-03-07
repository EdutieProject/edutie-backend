package com.edutie.infrastructure.knowledgemap.knowledgesubject;

import com.edutie.application.management.knowledgesubject.view.KnowledgeSubjectSearchView;
import com.edutie.infrastructure.knowledgemap.common.KnowledgeMapService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.KnowledgeSubjectSearchSchema;
import validation.WrapperResult;

import java.util.List;

public interface KnowledgeSubjectSearchService extends KnowledgeMapService {
    WrapperResult<List<KnowledgeSubjectSearchView>> search(KnowledgeSubjectSearchSchema schema);
}
