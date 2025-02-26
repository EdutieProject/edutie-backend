package com.edutie.domain.service.personalization.learningresource.implementation;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.selectionengine.PersonalizationRuleSelectionEngine;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.service.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.domain.service.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.infrastructure.external.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class LearningResourcePersonalizationServiceImplementation implements LearningResourcePersonalizationService {
    private final LearningResultPersistence learningResultPersistence;
    private final LargeLanguageModelService largeLanguageModelService;
    private final PersonalizationRuleSelectionEngine ruleSelectionEngine;

    @Override
    public WrapperResult<LearningExperience> personalize(LearningResourceDefinitionBase learningResourceDefinition, Student student) {
        Set<PersonalizationRule<?>> personalizationRules = ruleSelectionEngine.chooseRules(
                student, learningResourceDefinition.getLearningRequirements());
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(
                student,
                learningResultPersistence,
                learningResourceDefinition,
                personalizationRules
        );
        return largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema);
    }
}
