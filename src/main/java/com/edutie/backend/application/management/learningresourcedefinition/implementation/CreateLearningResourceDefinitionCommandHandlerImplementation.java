package com.edutie.backend.application.management.learningresourcedefinition.implementation;

import com.edutie.backend.application.management.learningresourcedefinition.CreateLearningResourceDefinitionCommandHandler;
import com.edutie.backend.application.management.learningresourcedefinition.commands.CreateLearningResourceDefinitionCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateLearningResourceDefinitionCommandHandlerImplementation implements CreateLearningResourceDefinitionCommandHandler {
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<LearningResourceDefinition> handle(CreateLearningResourceDefinitionCommand command) {
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                educator,
                PromptFragment.of(command.theoryDescription()),
                PromptFragment.of(command.exerciseDescription()),
                PromptFragment.of(command.additionalSummaryDescription()),
                PromptFragment.of(command.additionalHintsDescription())
        );
        return WrapperResult.successWrapper(learningResourceDefinition);
    }
}
