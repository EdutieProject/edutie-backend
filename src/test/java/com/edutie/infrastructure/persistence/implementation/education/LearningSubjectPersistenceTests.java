package com.edutie.infrastructure.persistence.implementation.education;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.EducatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class LearningSubjectPersistenceTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	private final Science science = Science.create(educator).getValue();
	@Autowired
	LearningSubjectPersistence learningSubjectPersistence;
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
		LearningSubject learningSubject = LearningSubject.createBlank(educator);
		assert learningSubjectPersistence.save(learningSubject).isSuccess();

		LearningSubject fetched = learningSubjectPersistence.getById(learningSubject.getId()).getValue();
		assert fetched.equals(learningSubject);
	}

	@Test
	public void wholeSaveTest() {
		LearningSubject learningSubject = LearningSubject.createBlank(educator);
		learningSubject.appendRequirement("hello", PromptFragment.of(""));
		learningSubject.appendRequirement("universe!", PromptFragment.of(""));
		Result result = learningSubjectPersistence.save(learningSubject);
		if (result.isFailure())
			throw new AssertionError(result.getError());

		LearningSubject fetched = learningSubjectPersistence.getById(learningSubject.getId()).getValue();
		assert fetched.equals(learningSubject);
		assert fetched.getRequirements().size() == 2;
	}
}
