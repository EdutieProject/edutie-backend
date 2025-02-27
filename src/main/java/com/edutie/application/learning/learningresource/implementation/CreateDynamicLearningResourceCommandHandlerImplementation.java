package com.edutie.application.learning.learningresource.implementation;

import com.edutie.application.learning.learningresource.CreateDynamicLearningResourceCommandHandler;
import com.edutie.application.learning.learningresource.commands.CreateDynamicLearningResourceCommand;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.DynamicLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.valueobjects.DynamicContext;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import com.edutie.domain.service.personalization.learningrequirement.DynamicLearningRequirementSelectionService;
import com.edutie.domain.service.personalization.learningresource.LearningResourcePersonalizationService;
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
    private final LearningExperiencePersistence learningExperiencePersistence;


    @Override
    public WrapperResult<LearningExperience> handle(CreateDynamicLearningResourceCommand command) {
        log.info("Creating dynamic learning resource for student user of id {} using a random fact:\n\"{}\"", command.studentUserId(), command.contextText());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        DynamicLearningResourceDefinition definition = DynamicLearningResourceDefinition.create(
                new DynamicContext(PromptFragment.of(command.contextText()), command.contextType()),
                learningRequirementSelectionService.selectRequirementsForStudent(student).getValue()
        );
        LearningExperience learningExperience = learningResourcePersonalizationService.personalize(definition, student).getValue();
        learningExperiencePersistence.save(learningExperience).throwIfFailure();
        return WrapperResult.successWrapper(learningExperience);
    }
}
