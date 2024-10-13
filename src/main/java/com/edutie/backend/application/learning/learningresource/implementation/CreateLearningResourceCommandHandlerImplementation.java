package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.activity.ActivityPersonalizationService;
import com.edutie.backend.domainservice.personalization.theory.TheoryPersonalizationService;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateLearningResourceCommandHandlerImplementation extends HandlerBase implements CreateLearningResourceCommandHandler {
    // ==== Persistence ====
    private final StudentPersistence studentPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final LearningResultPersistence learningResultPersistence;
    // ==== Services ====
    private final ActivityPersonalizationService activityPersonalizationService;
    private final TheoryPersonalizationService theoryPersonalizationService;
    private final KnowledgeMapService knowledgeMapService;
    private final LargeLanguageModelService largeLanguageModelService;

    @Override
    public WrapperResult<LearningResource> handle(CreateLearningResourceCommand command) {
        log.info("Creating learning resource for student user of id {}", command.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningResourceDefinition learningResourceDefinition = learningResourceDefinitionPersistence.getById(command.learningResourceDefinitionId()).getValue();
        Set<KnowledgeCorrelation> knowledgeCorrelations = knowledgeMapService.getKnowledgeCorrelations(learningResourceDefinition.getKnowledgeSubjectIds()).getValue();
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(
                learningResultPersistence, student, learningResourceDefinition.getLearningRequirements(), knowledgeCorrelations,
                activityPersonalizationService.personalize(learningResourceDefinition.getActivityDetails(), student, knowledgeCorrelations).getValue(),
                theoryPersonalizationService.personalize(learningResourceDefinition.getTheoryDetails(), student, knowledgeCorrelations).getValue()
        );
        LearningResource learningResource = largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema).getValue();
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return WrapperResult.successWrapper(learningResource);
    }
}
