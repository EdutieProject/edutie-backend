package com.edutie.infrastructure.llm.learningsubject;

import com.edutie.infrastructure.llm.common.LargeLanguageModelService;
import com.edutie.infrastructure.llm.learningsubject.schema.StudentObjectiveGenerationSchema;

public interface StudentObjectiveGenerationService extends LargeLanguageModelService<String, StudentObjectiveGenerationSchema> {
}
