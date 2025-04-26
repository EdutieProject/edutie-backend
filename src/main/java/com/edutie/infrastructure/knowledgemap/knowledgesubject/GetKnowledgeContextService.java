package com.edutie.infrastructure.knowledgemap.knowledgesubject;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.infrastructure.knowledgemap.common.KnowledgeMapService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeContextSchema;
import validation.WrapperResult;

public interface GetKnowledgeContextService extends KnowledgeMapService {
    WrapperResult<PromptFragment> getContext(GetKnowledgeContextSchema schema);
}
