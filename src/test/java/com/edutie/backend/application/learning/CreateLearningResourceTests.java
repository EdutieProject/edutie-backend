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
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.solutionsubmission.persistence.SolutionSubmissionPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationService;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationServiceImplementation;
import com.edutie.backend.mocks.LearningMocks;
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
    @Autowired
    SolutionSubmissionPersistence solutionSubmissionPersistence;
    @Autowired
    LearningResultPersistence learningResultPersistence;

    LearningResourceGenerationService learningResourceGenerationService;

    CreateLearningResourceCommandHandler createLearningResourceCommandHandler;

    @BeforeEach
    public void testSetup() {
        learningResourceGenerationService = new LearningResourceGenerationServiceImplementation(
                LearningMocks.knowledgeMapServiceMock(),
                LearningMocks.largeLanguageModelServiceMock()
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

        assert learningResourceWrapperResult.isSuccess();
    }

    @Test
    public void createLearningResourceWithLearningHistory() {
        // Create learning history
        Student student = Student.create(userId);
        studentPersistence.save(student).throwIfFailure();

        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setName("Integration by parts");
        learningRequirement.setDescription(PromptFragment.of("Here would go the description of integration by parts"));
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId(UUID.fromString("73658904-a20e-41f0-8274-6c000e0760da")));
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

        SolutionSubmission solutionSubmission = SolutionSubmission.create(student, learningResourceDefinition, "My report!", 0);
        solutionSubmissionPersistence.save(solutionSubmission).throwIfFailure();
        LearningResult learningResult = LearningResult.create(student, solutionSubmission, new Feedback("Feedback!", FeedbackType.NEUTRAL));
        learningResult.addAssessment(Assessment.create(learningRequirement.getId(), new Grade(5)));
        learningResultPersistence.save(learningResult).throwIfFailure();

        // Create a resource definition
        LearningRequirement requirement = LearningRequirement.create(educator);
        requirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        requirement.appendSubRequirement("SUBREQ1");
        requirement.appendSubRequirement("SUBREQ2");
        requirement.appendSubRequirement("SUBREQ3");
        learningRequirementPersistence.save(requirement).throwIfFailure();
        LearningResourceDefinition definition = LearningResourceDefinition.create(
                PromptFragment.of("Theory DESC!"),
                PromptFragment.of("Exercise DESC!"),
                Set.of(requirement)
        );
        definition.setHintsAdditionalDescription(PromptFragment.of("ADDITIONAL DESC FOR HINTS"));
        definition.setTheorySummaryAdditionalDescription(PromptFragment.of("ADDITIONAL SUMMARY DESC"));
        learningResourceDefinitionPersistence.save(definition).throwIfFailure();


        CreateLearningResourceCommand command = new CreateLearningResourceCommand()
                .learningResourceDefinitionId(definition.getId())
                .studentUserId(userId);
        WrapperResult<LearningResource> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command).throwIfFailure();
    }
}
