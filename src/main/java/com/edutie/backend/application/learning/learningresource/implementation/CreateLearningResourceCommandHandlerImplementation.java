package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
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
    public WrapperResult<LearningResource> handle(CreateLearningResourceCommand command) {
        log.info("Creating learning resource for student user of id {}", command.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        StaticLearningResourceDefinition staticLearningResourceDefinition = learningResourceDefinitionPersistence.getById(command.learningResourceDefinitionId()).getValue();
        LearningResource learningResource = learningResourcePersonalizationService.personalize(staticLearningResourceDefinition, student).getValue();
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return WrapperResult.successWrapper(learningResource);
    }
}
