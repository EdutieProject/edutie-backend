package com.edutie.application.management.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.learningsubject.query.SearchKnowledgeSubjectsQuery;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import validation.WrapperResult;

import java.util.List;

public interface SearchKnowledgeSubjectsQueryHandler extends UseCaseHandler<WrapperResult<List<KnowledgeSubject>>, SearchKnowledgeSubjectsQuery> {
}
