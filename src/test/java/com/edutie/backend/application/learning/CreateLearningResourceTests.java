package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.implementation.CreateLearningResourceCommandHandlerImplementation;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationService;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
public class CreateLearningResourceTests {
    private final UserId userId = new UserId();
    private final Administrator administrator = Administrator.create(userId);
    private final Educator educator = Educator.create(userId, administrator);
    @Autowired
    LearningRequirementPersistence learningRequirementPersistence;
    @Autowired
    AdministratorPersistence administratorPersistence;
    @Autowired
    EducatorPersistence educatorPersistence;
    @Autowired
    StudentPersistence studentPersistence;
    @Autowired
    LearningResourcePersistence learningResourcePersistence;
    @Autowired
    LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;

    LearningResourceGenerationService learningResourceGenerationService;

    CreateLearningResourceCommandHandler createLearningResourceCommandHandler;

    @BeforeEach
    public void testSetup() {
        learningResourceGenerationService = new LearningResourceGenerationServiceImplementation(
                knowledgeSubjectId -> WrapperResult.successWrapper(List.of(
                        new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("73658904-a20e-41f0-8274-6c000e0760da")), 2),
                        new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("4e92752a-5ef8-420e-ba45-260b6b7af5fe")), 4),
                        new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("201b3e63-5340-4a35-8f51-8de8275dae1e")), 7),
                        new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("7ad5fd80-6337-4b69-8048-8a97e39aa963")), 8)
                )),
                learningResourceGenerationSchema -> {
                    LearningResource learningResource = LearningResource.create(
                            learningResourceGenerationSchema,
                            learningResourceGenerationSchema.getLearningResourceDefinition().getExerciseDescription().text(),
                            Set.of(Hint.create("Hello!"), Hint.create("World!")),
                            learningResourceGenerationSchema.getLearningResourceDefinition().getTheoryDescription().text(),
                            learningResourceGenerationSchema.getLearningResourceDefinition().getTheorySummaryAdditionalDescription().text()
                    );
                    return WrapperResult.successWrapper(learningResource);
                }
        );

        createLearningResourceCommandHandler = new CreateLearningResourceCommandHandlerImplementation(
                studentPersistence,
                learningResourceDefinitionPersistence,
                learningResourcePersistence,
                learningResourceGenerationService
        );

        administratorPersistence.save(administrator);
        educatorPersistence.save(educator);
    }


    @Test
    public void createLearningResourceForEmptyLearningHistory() {
        Student student = Student.create(userId);
        studentPersistence.save(student).throwIfFailure();

        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setName("Integration by parts");
        learningRequirement.setDescription(PromptFragment.of("Here would go the description of integration by parts"));
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.appendSubRequirement("Calculating derivatives and antiderivatives of ingredient functions");
        learningRequirement.appendSubRequirement("Proper formula usage");
        learningRequirement.appendSubRequirement("3rd sub req nfgoiufguoeoeaofsoefe");
        learningRequirementPersistence.save(learningRequirement).throwIfFailure();

        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                PromptFragment.of("Theory description"),
                PromptFragment.of("Exercise description"),
                Set.of(learningRequirement)
        );
        learningResourceDefinition.setTheorySummaryAdditionalDescription(PromptFragment.of("Theory summary additional desc"));
        learningResourceDefinition.setHintsAdditionalDescription(PromptFragment.of("Hints additional desc"));
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

        CreateLearningResourceCommand command = new CreateLearningResourceCommand()
                .learningResourceDefinitionId(learningResourceDefinition.getId())
                .studentUserId(userId);
        WrapperResult<LearningResource> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command).throwIfFailure();
    }
}
