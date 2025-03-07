package com.edutie.application.management.knowledgesubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.knowledgesubject.query.SearchKnowledgeSubjectsQuery;
import com.edutie.application.management.knowledgesubject.view.KnowledgeSubjectSearchView;
import validation.WrapperResult;

import java.util.List;

public interface SearchKnowledgeSubjectsQueryHandler extends UseCaseHandler<WrapperResult<List<KnowledgeSubjectSearchView>>, SearchKnowledgeSubjectsQuery> {
}
