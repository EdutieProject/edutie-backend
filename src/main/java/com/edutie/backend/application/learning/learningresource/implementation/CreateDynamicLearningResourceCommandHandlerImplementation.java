package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.learning.learningresource.CreateDynamicLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateDynamicLearningResourceCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.DynamicLearningResourceDefinition;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningrequirement.DynamicLearningRequirementSelectionService;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateDynamicLearningResourceCommandHandlerImplementation implements CreateDynamicLearningResourceCommandHandler {
    private final StudentPersistence studentPersistence;
    private final DynamicLearningRequirementSelectionService learningRequirementSelectionService;
    private final LearningResourcePersonalizationService learningResourcePersonalizationService;
    private final LearningResourcePersistence learningResourcePersistence;


    @Override
    public WrapperResult<LearningResource> handle(CreateDynamicLearningResourceCommand command) {
        log.info("Creating dynamic learning resource for student user of id {} using a random fact:\n\"{}\"", command.studentUserId(), command.context());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        DynamicLearningResourceDefinition definition = DynamicLearningResourceDefinition.create(
                PromptFragment.of(command.context()),
                learningRequirementSelectionService.selectRequirementsForStudent(student).getValue()
        );
        LearningResource learningResource = learningResourcePersonalizationService.personalize(definition, student).getValue();
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return WrapperResult.successWrapper(learningResource);
    }
}
