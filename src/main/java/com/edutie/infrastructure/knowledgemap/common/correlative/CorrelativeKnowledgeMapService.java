package com.edutie.infrastructure.knowledgemap.common.correlative;

import com.edutie.infrastructure.knowledgemap.common.generative.KnowledgeMapGenerationSchema;
import validation.WrapperResult;

public interface CorrelativeKnowledgeMapService<TCorrelation, TCorrelationsSchema extends KnowledgeMapGenerationSchema> {
    WrapperResult<TCorrelation> getCorrelation(TCorrelationsSchema correlationsSchema);
}
