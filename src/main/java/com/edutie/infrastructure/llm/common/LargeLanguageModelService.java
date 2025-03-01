package com.edutie.infrastructure.llm.common;

import com.edutie.infrastructure.llm.common.schema.GenerationSchema;
import validation.WrapperResult;

/**
 * Supertype for LLM-based services.
 */
public interface LargeLanguageModelService<TGenerated, TGenerationSchema extends GenerationSchema> {
    WrapperResult<TGenerated> generate(TGenerationSchema generationSchema);
}
