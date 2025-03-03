package com.edutie.infrastructure.llm.common;

import com.edutie.infrastructure.llm.common.schema.LlmGenerationSchema;
import validation.WrapperResult;

/**
 * Supertype for LLM-based services.
 */
public interface LargeLanguageModelService<TGenerated, TGenerationSchema extends LlmGenerationSchema> {
    WrapperResult<TGenerated> generate(TGenerationSchema generationSchema);
}
