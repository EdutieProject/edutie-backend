package com.edutie.backend.domainservice.personalization.learningresource.implementation;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.selectionengine.PersonalizationRuleSelectionEngine;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.infrastructure.external.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastructure.external.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class LearningResourcePersonalizationServiceImplementation implements LearningResourcePersonalizationService {
    private final KnowledgeMapService knowledgeMapService;
    private final LearningResultPersistence learningResultPersistence;
    private final LargeLanguageModelService largeLanguageModelService;

    @Override
    public WrapperResult<LearningResource> personalize(LearningResourceDefinitionBase learningResourceDefinition, Student student) {
        Set<PersonalizationRule<?>> personalizationRules = new PersonalizationRuleSelectionEngine(student, knowledgeMapService)
                .chooseRulesByRequirementsAndHistory(learningResourceDefinition.getLearningRequirements(), student.getLatestLearningResults(learningResultPersistence));
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(
                student,
                learningResultPersistence,
                learningResourceDefinition,
                personalizationRules
        );
        return largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema);
    }
}
