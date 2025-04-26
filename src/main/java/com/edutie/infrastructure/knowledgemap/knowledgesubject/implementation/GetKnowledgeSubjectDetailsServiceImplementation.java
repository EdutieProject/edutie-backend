package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation;

import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import com.edutie.infrastructure.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.common.ExternalService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.GetKnowledgeSubjectDetailsService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation.dto.KnowledgeSubjectDetailsDto;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeSubjectDetailsSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetKnowledgeSubjectDetailsServiceImplementation extends ExternalService implements GetKnowledgeSubjectDetailsService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;

    @Override
    public WrapperResult<KnowledgeSubjectDetailsView> getDetails(GetKnowledgeSubjectDetailsSchema schema) {
        log.info("Fetching knowledge subject details by search schema: {}", schema);
        final String knowledgeSubjectSearchUrl = KNOWLEDGE_MAP_URL + "/knowledge-subjects/details";
        return new ExternalInfrastructureHandler<KnowledgeSubjectDetailsView, GetKnowledgeSubjectDetailsSchema, KnowledgeSubjectDetailsDto>(this.getClass())
                .setActionUrl(knowledgeSubjectSearchUrl)
                .handle(schema, KnowledgeSubjectDetailsDto.class);
    }
}
