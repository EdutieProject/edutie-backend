package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation;

import com.edutie.application.management.knowledgesubject.view.KnowledgeSubjectSearchView;
import com.edutie.infrastructure.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.common.ExternalService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.KnowledgeSubjectSearchService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation.dto.KnowledgeSubjectSearchResultsDto;
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
public class KnowledgeSubjectSearchServiceImplementation extends ExternalService implements KnowledgeSubjectSearchService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;

    @Override
    public WrapperResult<List<KnowledgeSubjectSearchView>> search(KnowledgeSubjectSearchSchema schema) {
        log.info("Searching knowledge subjects by search schema: {}", schema);
        final String knowledgeSubjectSearchUrl = KNOWLEDGE_MAP_URL + "/knowledge-subjects/search";
        return new ExternalInfrastructureHandler<List<KnowledgeSubjectSearchView>, KnowledgeSubjectSearchSchema, KnowledgeSubjectSearchResultsDto>(this.getClass())
                .setActionUrl(knowledgeSubjectSearchUrl)
                .handle(schema, KnowledgeSubjectSearchResultsDto.class);
    }
}
