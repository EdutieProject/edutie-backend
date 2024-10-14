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
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.ExternalServiceMocks;
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
	LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
	@Autowired
	LearningResourcePersonalizationService learningResourcePersonalizationService;

	CreateLearningResourceCommandHandler createLearningResourceCommandHandler;

	@BeforeEach
	public void testSetup() {
		createLearningResourceCommandHandler = new CreateLearningResourceCommandHandlerImplementation(
				studentPersistence,
				learningResourceDefinitionPersistence,
				learningResourcePersistence,
				learningResourcePersonalizationService
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
