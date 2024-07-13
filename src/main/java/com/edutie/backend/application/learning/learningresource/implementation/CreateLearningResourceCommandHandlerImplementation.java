package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateLearningResourceCommandHandlerImplementation extends HandlerBase implements CreateLearningResourceCommandHandler {
    private final StudentPersistence studentPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final LearningResourceGenerationService learningResourceGenerationService;

    @Override
    public WrapperResult<LearningResource> handle(CreateLearningResourceCommand command) {
        LOGGER.info("Creating learning resource for student of id {}", command.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningResourceDefinition learningResourceDefinition = learningResourceDefinitionPersistence.getById(command.learningResourceDefinitionId()).getValue();
        LearningResource learningResource = learningResourceGenerationService.generateLearningResource(learningResourceDefinition, student).getValue();
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return WrapperResult.successWrapper(learningResource);
    }
}
