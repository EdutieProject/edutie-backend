package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.KnowledgeSubjectSearchService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.KnowledgeSubjectSearchSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KnowledgeSubjectSearchServiceImplementation implements KnowledgeSubjectSearchService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;

    @Override
    public WrapperResult<List<KnowledgeSubject>> search(KnowledgeSubjectSearchSchema schema) {
        log.info("Searching knowledge subjects by search schema: {}", schema);
        //TODO
        return WrapperResult.successWrapper(List.of(
                KnowledgeSubject.create(new KnowledgeSubjectId(), "Blue banana"),
                KnowledgeSubject.create(new KnowledgeSubjectId(), "Us Dollar"),
                KnowledgeSubject.create(new KnowledgeSubjectId(), "Quadratic equation")
        ));
    }
}
