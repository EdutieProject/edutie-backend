package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation.dto;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeContextSchema;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class KnowledgeContextDto implements ExternalServiceDto<PromptFragment, GetKnowledgeContextSchema> {
    private final String knowledgeContext;

    @JsonCreator
    public KnowledgeContextDto(String knowledgeContext) {
        this.knowledgeContext = knowledgeContext;
    }


    @Override
    public PromptFragment intoDomainEntity(GetKnowledgeContextSchema getKnowledgeContextSchema) {
        return PromptFragment.of(knowledgeContext);
    }
}
