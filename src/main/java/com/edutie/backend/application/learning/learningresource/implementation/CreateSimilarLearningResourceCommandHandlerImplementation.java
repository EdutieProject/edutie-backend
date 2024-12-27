package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.learning.learningresource.CreateSimilarLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateSimilarLearningResourceCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.DynamicLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.valueobjects.DynamicContext;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
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
    public WrapperResult<LearningResource> handle(CreateSimilarLearningResourceCommand command) {
        log.info("Creating a similar learning resource for student user of id {} using a LR-Id: {}", command.studentUserId(), command.learningResourceId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningResource learningResource = learningResourcePersistence.getById(command.learningResourceId()).getValue();
        StaticLearningResourceDefinition definition = learningResourceDefinitionPersistence.getById(learningResource.getDefinitionId()).getValue();
        LearningResource newLearningResource = learningResourcePersonalizationService.personalize(definition, student).getValue();
        learningResourcePersistence.save(newLearningResource).throwIfFailure();
        return WrapperResult.successWrapper(newLearningResource);
    }
}
