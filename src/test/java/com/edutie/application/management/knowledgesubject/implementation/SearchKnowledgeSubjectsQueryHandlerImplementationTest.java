package com.edutie.application.management.knowledgesubject.implementation;

import com.edutie.application.management.knowledgesubject.SearchKnowledgeSubjectsQueryHandler;
import com.edutie.application.management.knowledgesubject.query.SearchKnowledgeSubjectsQuery;
import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SearchKnowledgeSubjectsQueryHandlerImplementationTest {

    private SearchKnowledgeSubjectsQueryHandler handler;

    @Test
    public void handle() {
        handler = new SearchKnowledgeSubjectsQueryHandlerImplementation(
                schema -> WrapperResult.successWrapper(List.of(
                        new KnowledgeSubjectDetailsView(KnowledgeSubjectReference.create(new KnowledgeSubjectId()), "Blue banana", "Area in Europe"),
                        new KnowledgeSubjectDetailsView(KnowledgeSubjectReference.create(new KnowledgeSubjectId()), "Us Dollar", "A currency"),
                        new KnowledgeSubjectDetailsView(KnowledgeSubjectReference.create(new KnowledgeSubjectId()), "Banana", "Fruit")
                ))
        );

        SearchKnowledgeSubjectsQuery query = new SearchKnowledgeSubjectsQuery()
                .educatorUserId(new UserId())
                .knowledgeSubjectSearchName("Blue");

        WrapperResult<List<KnowledgeSubjectDetailsView>> result = handler.handle(query);

        assertTrue(result.isSuccess());
        assertEquals(3, result.getValue().size());
    }
}