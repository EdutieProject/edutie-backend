package com.edutie.application.learning.learningresource.implementation;

import com.edutie.application.common.HandlerBase;
import com.edutie.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.application.learning.learningresource.commands.CreateLearningResourceCommand;
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

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateLearningResourceCommandHandlerImplementation extends HandlerBase implements CreateLearningResourceCommandHandler {
    private final StudentPersistence studentPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final LearningResourcePersonalizationService learningResourcePersonalizationService;

    @Override
    public WrapperResult<LearningExperience> handle(CreateLearningResourceCommand command) {
        log.info("Creating learning resource for student user of id {}", command.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        StaticLearningResourceDefinition staticLearningResourceDefinition = learningResourceDefinitionPersistence.getById(command.learningResourceDefinitionId()).getValue();
        LearningExperience learningExperience = learningResourcePersonalizationService.personalize(staticLearningResourceDefinition, student).getValue();
        learningResourcePersistence.save(learningExperience).throwIfFailure();
        return WrapperResult.successWrapper(learningExperience);
    }
}
