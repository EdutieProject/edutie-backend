package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.learning.learningresource.CreateRandomFactDynamicLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateRandomFactDynamicLearningResourceCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.PersonalizationError;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationSchemaService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
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
    private final LearningResourceGenerationSchemaService learningResourceGenerationSchemaService;
    private final LargeLanguageModelService largeLanguageModelService;

    @Override
    public WrapperResult<LearningResource> handle(CreateRandomFactDynamicLearningResourceCommand command) {
        log.info("Creating dynamic learning resource for student user of id {} using a random fact:\n\"{}\"", command.studentUserId(), command.randomFact());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        List<LearningResult> latestLearningResults = learningResultPersistence.getLatestResultsOfStudent(student.getId(), 1, LocalDateTime.MIN).getValue();
        if (latestLearningResults.isEmpty())
            return WrapperResult.failureWrapper(PersonalizationError.cantCreateDynamicResourceNoLatestResults());
        LearningResourceDefinition learningResourceDefinition = latestLearningResults.getFirst().getLearningResourceDefinition();
        learningResourceDefinition.setExerciseDescription(
                PromptFragment.of(String.format("""
                Exercise must be related to the provided random fact:
                <random-fact>%s</random-fact>
                Exercise should utilize the provided data and utilize it to create an exercise in a creative way.
                """, command.randomFact())));
        LearningResourceGenerationSchema learningResourceGenerationSchema = learningResourceGenerationSchemaService.createSchema(learningResourceDefinition, student).getValue();
        LearningResource learningResource = largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema).getValue();
        return WrapperResult.successWrapper(learningResource);
    }
}
