package com.edutie.application.management.knowledgesubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.knowledgesubject.query.SearchKnowledgeSubjectsQuery;
import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import validation.WrapperResult;

import java.util.List;

public interface SearchKnowledgeSubjectsQueryHandler extends UseCaseHandler<WrapperResult<List<KnowledgeSubjectDetailsView>>, SearchKnowledgeSubjectsQuery> {
}
