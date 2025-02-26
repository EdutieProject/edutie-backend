package com.edutie.infrastructure.persistence.implementation.education;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningrequirement.LearningRequirement;
import com.edutie.domain.core.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.infrastructure.persistence.jpa.repositories.EducatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class LearningRequirementPersistenceTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	private final Science science = Science.create(educator).getValue();
	@Autowired
	LearningRequirementPersistence learningRequirementPersistence;
	@Autowired
	EducatorRepository educatorRepository;
	@Autowired
	ScienceRepository scienceRepository;

	@BeforeEach
	public void testSetup() {
		educatorRepository.save(educator);
		scienceRepository.save(science);
	}

	@Test
	public void defaultSaveTest() {
		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		assert learningRequirementPersistence.save(learningRequirement).isSuccess();

		LearningRequirement fetched = learningRequirementPersistence.getById(learningRequirement.getId()).getValue();
		assert fetched.equals(learningRequirement);
	}

	@Test
	public void wholeSaveTest() {
		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.appendSubRequirement("hello", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("universe!", PromptFragment.of(""));
		Result result = learningRequirementPersistence.save(learningRequirement);
		if (result.isFailure())
			throw new AssertionError(result.getError());

		LearningRequirement fetched = learningRequirementPersistence.getById(learningRequirement.getId()).getValue();
		assert fetched.equals(learningRequirement);
		assert fetched.getElementalRequirements().size() == 2;
	}
}
