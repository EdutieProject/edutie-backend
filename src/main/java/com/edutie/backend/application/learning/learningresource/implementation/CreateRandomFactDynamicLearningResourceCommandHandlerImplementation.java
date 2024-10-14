package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.learning.learningresource.CreateRandomFactDynamicLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateRandomFactDynamicLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateRandomFactDynamicLearningResourceCommandHandlerImplementation implements CreateRandomFactDynamicLearningResourceCommandHandler {
    private final StudentPersistence studentPersistence;
    private final LearningResultPersistence learningResultPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final LearningResourcePersonalizationService learningResourcePersonalizationService;


    @Override
    public WrapperResult<LearningResource> handle(CreateRandomFactDynamicLearningResourceCommand command) {
        log.info("Creating dynamic learning resource for student user of id {} using a random fact:\n\"{}\"", command.studentUserId(), command.randomFact());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        List<LearningResult> latestLearningResults = learningResultPersistence.getLatestResultsOfStudent(student.getId(), 1, LocalDateTime.now().minusDays(7)).getValue();
        LearningResourceDefinition learningResourceDefinition = latestLearningResults.isEmpty() ?
                learningResourceDefinitionPersistence.getAny().getValue() :
                learningResourceDefinitionPersistence.getById(latestLearningResults.getFirst().getLearningResourceDefinitionId()).getValue();
        LearningResource learningResource = learningResourcePersonalizationService.personalize(
                learningResourceDefinition.adjustRandomFactExercise(command.randomFact()), student).getValue();
        return WrapperResult.successWrapper(learningResource);
    }
}
