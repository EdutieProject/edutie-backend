package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.infrastructure.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.common.ExternalService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.GetKnowledgeContextService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation.dto.KnowledgeContextDto;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeContextSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetKnowledgeContextServiceImplementation extends ExternalService implements GetKnowledgeContextService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;
    @Override
    public WrapperResult<PromptFragment> getContext(GetKnowledgeContextSchema schema) {
        log.info("Retrieving knowledge context using schema {}", schema);
        final String knowledgeContextUrl = KNOWLEDGE_MAP_URL + "/knowledge-context/broad-context";
        return new ExternalInfrastructureHandler<PromptFragment, GetKnowledgeContextSchema, KnowledgeContextDto>(this.getClass())
                .setActionUrl(knowledgeContextUrl)
                .handle(schema, KnowledgeContextDto.class);
    }
}
