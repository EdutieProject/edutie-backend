package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.learning.learningresource.CreateRandomFactDynamicLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateRandomFactDynamicLearningResourceCommand;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.DynamicLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningrequirement.DynamicLearningRequirementSelectionService;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateRandomFactDynamicLearningResourceCommandHandlerImplementation implements CreateRandomFactDynamicLearningResourceCommandHandler {
    private final StudentPersistence studentPersistence;
    private final DynamicLearningRequirementSelectionService learningRequirementSelectionService;
    private final LearningResourcePersonalizationService learningResourcePersonalizationService;
    private final LearningResourcePersistence learningResourcePersistence;


    @Override
    public WrapperResult<LearningResource> handle(CreateRandomFactDynamicLearningResourceCommand command) {
        log.info("Creating dynamic learning resource for student user of id {} using a random fact:\n\"{}\"", command.studentUserId(), command.randomFact());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        DynamicLearningResourceDefinition definition = DynamicLearningResourceDefinition.createRandomFact(
                command.randomFact(),
                learningRequirementSelectionService.selectRequirementsForStudent(student).getValue()
        );
        LearningResource learningResource = learningResourcePersonalizationService.personalize(definition, student).getValue();
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return WrapperResult.successWrapper(learningResource);
    }
}
