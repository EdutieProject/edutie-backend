package com.edutie.backend.domainservice.personalization.learningresource.implementation;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@RequiredArgsConstructor
@Component
public class LearningResourcePersonalizationServiceImplementation implements LearningResourcePersonalizationService {
    private final KnowledgeMapService knowledgeMapService;
    private final LearningResultPersistence learningResultPersistence;
    private final LargeLanguageModelService largeLanguageModelService;

    @Override
    public WrapperResult<LearningResource> personalize(LearningResourceDefinition learningResourceDefinition, Student student) {
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(
                student,
                learningResultPersistence,
                knowledgeMapService.getKnowledgeCorrelations(learningResourceDefinition.getKnowledgeSubjectIds()).getValue(),
                learningResourceDefinition
        );
        return largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema);
    }
}
