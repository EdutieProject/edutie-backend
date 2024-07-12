package com.edutie.backend.application.learning.learningresource.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities.PersonalizationRule;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateLearningResourceCommandHandlerImplementation extends HandlerBase implements CreateLearningResourceCommandHandler {
    private final StudentPersistence studentPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final KnowledgeMapService knowledgeMapService;
    private final LargeLanguageModelService largeLanguageModelService;
    @Override
    public WrapperResult<LearningResource> handle(CreateLearningResourceCommand command) {
        LOGGER.info("Creating learning resource for student of id {}", command.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(command.studentUserId());
        LearningResourceDefinition learningResourceDefinition = learningResourceDefinitionPersistence.getById(command.learningResourceDefinitionId()).getValue();
        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(learningResourceDefinition);
        for (LearningRequirement learningRequirement : learningResourceDefinition.getLearningRequirements()) {
            for (KnowledgeCorrelation knowledgeCorrelation : knowledgeMapService.getKnowledgeCorrelations(learningRequirement.getKnowledgeSubjectId()).getValue()) {
                PersonalizationRule personalizationRule = new PersonalizationRule(
                        knowledgeCorrelation,
                        learningRequirement.getId(),
                        student.getLearningHistoryByLearningRequirement(learningRequirement.getId()),
                        learningRequirement.getSubRequirements().size()
                );
                learningResourceGenerationSchema.addPersonalizationRule(personalizationRule);
            }
        }
        LearningResource learningResource = largeLanguageModelService.generateLearningResource(learningResourceGenerationSchema).getValue();
        return WrapperResult.successWrapper(learningResource);
    }
}
