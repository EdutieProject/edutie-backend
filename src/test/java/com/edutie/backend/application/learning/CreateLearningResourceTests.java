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
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
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
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationSchemaService;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourceGenerationSchemaServiceImplementation;
import com.edutie.backend.mocks.LearningMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.WrapperResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

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

	LearningResourceGenerationSchemaService learningResourceGenerationSchemaService;

	CreateLearningResourceCommandHandler createLearningResourceCommandHandler;

	@BeforeEach
	public void testSetup() {
		learningResourceGenerationSchemaService = new LearningResourceGenerationSchemaServiceImplementation(LearningMocks.knowledgeMapServiceMock());

		createLearningResourceCommandHandler = new CreateLearningResourceCommandHandlerImplementation(studentPersistence, learningResourceDefinitionPersistence, learningResourcePersistence, learningResourceGenerationSchemaService, LearningMocks.largeLanguageModelServiceMock());

		administratorPersistence.save(administrator);
		educatorPersistence.save(educator);
	}


	@Test
	public void createLearningResourceForEmptyLearningHistory() {
		Student student = Student.create(userId);
		studentPersistence.save(student).throwIfFailure();

		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.setName("Integration by parts");
		learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
		learningRequirement.appendSubRequirement("Calculating derivatives and antiderivatives of ingredient functions", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("Proper formula usage", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("3rd sub req nfgoiufguoeoeaofsoefe", PromptFragment.of(""));
		learningRequirementPersistence.save(learningRequirement).throwIfFailure();

		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(PromptFragment.of("Theory description"), PromptFragment.of("Exercise description"), Set.of(learningRequirement));
		learningResourceDefinition.setTheorySummaryAdditionalDescription(PromptFragment.of("Theory summary additional desc"));
		learningResourceDefinition.setHintsAdditionalDescription(PromptFragment.of("Hints additional desc"));
		learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

		CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(learningResourceDefinition.getId()).studentUserId(userId);
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
		learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId(UUID.fromString("73658904-a20e-41f0-8274-6c000e0760da")));
		learningRequirement.appendSubRequirement("Calculating derivatives and antiderivatives of ingredient functions", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("Proper formula usage", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("3rd sub req nfgoiufguoeoeaofsoefe", PromptFragment.of(""));
		learningRequirementPersistence.save(learningRequirement).throwIfFailure();

		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(PromptFragment.of("Theory description"), PromptFragment.of("Exercise description"), Set.of(learningRequirement));
		learningResourceDefinition.setTheorySummaryAdditionalDescription(PromptFragment.of("Theory summary additional desc"));
		learningResourceDefinition.setHintsAdditionalDescription(PromptFragment.of("Hints additional desc"));
		learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
		LearningResource learningResource = LearningResource.create(LearningResourceGenerationSchema.create(learningResourceDefinition, student), Activity.create("", Set.of()), Theory.create("", ""), Set.of());

		SolutionSubmission solutionSubmission = SolutionSubmission.create(student, learningResource, "My report!", 0);
		solutionSubmissionPersistence.save(solutionSubmission).throwIfFailure();
		LearningResult learningResult = LearningResult.create(student, solutionSubmission, new Feedback("Feedback!", FeedbackType.NEUTRAL));
		learningResult.addAssessment(Assessment.create(learningRequirement.getId(), new Grade(5)));
		learningResultPersistence.save(learningResult).throwIfFailure();

		// Create a resource definition
		LearningRequirement requirement = LearningRequirement.create(educator);
		requirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
		requirement.appendSubRequirement("SUBREQ1", PromptFragment.of(""));
		requirement.appendSubRequirement("SUBREQ2", PromptFragment.of(""));
		requirement.appendSubRequirement("SUBREQ3", PromptFragment.of(""));
		learningRequirementPersistence.save(requirement).throwIfFailure();
		LearningResourceDefinition definition = LearningResourceDefinition.create(PromptFragment.of("Theory DESC!"), PromptFragment.of("Exercise DESC!"), Set.of(requirement));
		definition.setHintsAdditionalDescription(PromptFragment.of("ADDITIONAL DESC FOR HINTS"));
		definition.setTheorySummaryAdditionalDescription(PromptFragment.of("ADDITIONAL SUMMARY DESC"));
		learningResourceDefinitionPersistence.save(definition).throwIfFailure();


		CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(definition.getId()).studentUserId(userId);

		createLearningResourceCommandHandler.handle(command).throwIfFailure();
	}
}
