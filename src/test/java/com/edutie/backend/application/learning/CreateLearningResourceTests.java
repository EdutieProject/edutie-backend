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
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.ExternalServiceMocks;
import com.edutie.backend.mocks.PersonalizationServiceMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.WrapperResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.Set;

@SpringBootTest
public class CreateLearningResourceTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	private final Student student = Student.create(userId);
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
	LearningResultPersistence learningResultPersistence;
	@Autowired
	LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;

	CreateLearningResourceCommandHandler createLearningResourceCommandHandler;

	@BeforeEach
	public void testSetup() {
		createLearningResourceCommandHandler = new CreateLearningResourceCommandHandlerImplementation(
				studentPersistence,
				learningResourceDefinitionPersistence,
				learningResourcePersistence,
				learningResultPersistence,
				PersonalizationServiceMocks.activityPersonalizationServiceMock(),
				PersonalizationServiceMocks.theoryPersonalizationServiceMock(),
				ExternalServiceMocks.knowledgeMapServiceMock(),
				ExternalServiceMocks.largeLanguageModelServiceMock()
		);

		studentPersistence.save(student).throwIfFailure();
		administratorPersistence.save(administrator).throwIfFailure();
		educatorPersistence.save(educator).throwIfFailure();
	}


	@Test
	public void createLearningResourceForEmptyLearningHistory() {
		LearningRequirement learningRequirement = EducationMocks.independentLearningRequirement(educator);
		learningRequirementPersistence.save(learningRequirement).throwIfFailure();
		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
				educator,
				PromptFragment.of("Theory description from definition"),
				PromptFragment.of("Exercise description from definition"),
				Set.of(learningRequirement)
		);
		learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

		CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(learningResourceDefinition.getId()).studentUserId(userId);
		WrapperResult<LearningResource> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command).throwIfFailure();

		assert learningResourceWrapperResult.isSuccess();
	}

	@Test
	public void createLearningResourceWithLearningHistory() {
		createLearningResourceForEmptyLearningHistory(); // create learning result for student

		// Create a resource definition
		LearningRequirement relatedRequirement = EducationMocks.relatedLearningRequirement(educator);
		learningRequirementPersistence.save(relatedRequirement);
		LearningResourceDefinition definition = LearningResourceDefinition.create(
				educator,
				PromptFragment.of("Theory DESC!"),
				PromptFragment.of("Exercise DESC!"),
				Set.of(relatedRequirement)
		);
		learningResourceDefinitionPersistence.save(definition).throwIfFailure();


		CreateLearningResourceCommand command = new CreateLearningResourceCommand().learningResourceDefinitionId(definition.getId()).studentUserId(userId);
		WrapperResult<LearningResource> learningResourceWrapperResult = createLearningResourceCommandHandler.handle(command);

		assert learningResourceWrapperResult.isSuccess();
	}
}
