package com.edutie.infrastructure.knowledgemap.common.generative;

import validation.WrapperResult;

/**
 * Supertype for LLM-based services.
 */
//TODO: TContext extends KnowledgeContext?
public interface GenerativeKnowledgeMapService<TGenerated, TGenerationSchema extends KnowledgeMapGenerationSchema> {
    WrapperResult<TGenerated> generateContext(TGenerationSchema generationSchema);
}