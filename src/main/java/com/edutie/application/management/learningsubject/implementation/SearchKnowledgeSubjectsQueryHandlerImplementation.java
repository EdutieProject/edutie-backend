package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.SearchKnowledgeSubjectsQueryHandler;
import com.edutie.application.management.learningsubject.query.SearchKnowledgeSubjectsQuery;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.KnowledgeSubjectSearchService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.KnowledgeSubjectSearchSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SearchKnowledgeSubjectsQueryHandlerImplementation implements SearchKnowledgeSubjectsQueryHandler {
    private final KnowledgeSubjectSearchService knowledgeSubjectSearchService;

    @Override
    public WrapperResult<List<KnowledgeSubject>> handle(SearchKnowledgeSubjectsQuery query) {
        log.info("Searching knowledge subjects using name: {} by user of id {}", query.knowledgeSubjectSearchName(), query.educatorUserId());
        return query.knowledgeSubjectSearchName() == null ?
                WrapperResult.successWrapper(List.of())
                : knowledgeSubjectSearchService.search(new KnowledgeSubjectSearchSchema(query.knowledgeSubjectSearchName()));
    }
}
