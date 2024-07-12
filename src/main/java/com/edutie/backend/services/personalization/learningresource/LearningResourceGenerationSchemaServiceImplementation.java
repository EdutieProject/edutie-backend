package com.edutie.backend.services.personalization.learningresource;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.ProblemDescriptor;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class LearningResourceGenerationSchemaServiceImplementation implements LearningResourceGenerationSchemaService {
    private final KnowledgeMapService knowledgeMapService;
    private final LargeLanguageModelService largeLanguageModelService;
    @Override
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceDefinition lResDef, Student student) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(lResDef);
        for (ProblemDescriptor problemDescriptor : learningResourceGenerationSchema.getProblemDescriptors()) {
            //TODO: enrich problem descriptor
        }
    }
}
