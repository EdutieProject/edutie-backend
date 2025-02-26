package com.edutie.application.learning.learningresource.implementation;

import com.edutie.application.learning.learningresource.CreateSimilarLearningResourceCommandHandler;
import com.edutie.application.learning.learningresource.commands.CreateSimilarLearningResourceCommand;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import com.edutie.domain.service.personalization.learningresource.LearningResourcePersonalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateSimilarLearningResourceCommandHandlerImplementation implements CreateSimilarLearningResourceCommandHandler {
    private final LearningResourcePersistence learningResourcePersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final StudentPersistence studentPersistence;
    private final LearningResourcePersonalizationService learningResourcePersonalizationService;

    @Override
    public WrapperResult<LearningExperience> handle(CreateSimilarLearningResourceCommand command) {
        log.info("Creating a similar learning resource for student user of id {} using a LR-Id: {}", command.studentUserId(), command.learningResourceId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningExperience learningExperience = learningResourcePersistence.getById(command.learningResourceId()).getValue();
        StaticLearningResourceDefinition definition = learningResourceDefinitionPersistence.getById(learningExperience.getDefinitionId()).getValue();
        LearningExperience newLearningExperience = learningResourcePersonalizationService.personalize(definition, student).getValue();
        learningResourcePersistence.save(newLearningExperience).throwIfFailure();
        return WrapperResult.successWrapper(newLearningExperience);
    }
}
