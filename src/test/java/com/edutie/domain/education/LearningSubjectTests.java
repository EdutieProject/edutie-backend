package com.edutie.domain.education;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.enums.EducatorType;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class LearningSubjectTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(new UserId());
	private final Educator educator = Educator.create(userId, administrator);

	@BeforeEach
	public void testSetup() {
		educator.setType(EducatorType.ADMINISTRATOR);
	}

	@Test
	public void subRequirementsAppendTest() {
		LearningSubject learningSubject = LearningSubject.createBlank(educator);
		learningSubject.appendSubRequirement("R1", PromptFragment.of(""));
		learningSubject.appendSubRequirement("R2", PromptFragment.of(""));
		learningSubject.appendSubRequirement("R3", PromptFragment.of(""));

		assert learningSubject.getRequirements().get(0).getStudentObjective().text().equals("R1");
		assert learningSubject.getRequirements().get(2).getStudentObjective().text().equals("R3");
	}

	@Test
	public void subRequirementsInsertTest() {
		LearningSubject learningSubject = LearningSubject.createBlank(educator);
		learningSubject.appendSubRequirement("R1", PromptFragment.of(""));
		learningSubject.appendSubRequirement("R2", PromptFragment.of(""));
		learningSubject.appendSubRequirement("R3", PromptFragment.of(""));
		assert learningSubject.insertSubRequirement("Hello!", PromptFragment.of(""), 1).isSuccess();

		assert learningSubject.getRequirements().get(1).getStudentObjective().text().equals("Hello!");
	}

	@Test
	public void subRequirementDeleteTest() {
		LearningSubject learningSubject = LearningSubject.createBlank(educator);
		learningSubject.appendSubRequirement("R1", PromptFragment.of(""));
		learningSubject.appendSubRequirement("R2", PromptFragment.of(""));
		learningSubject.appendSubRequirement("R3", PromptFragment.of(""));
		assert learningSubject.getRequirements().size() == 3;

		assert learningSubject.removeRequirement(0).isSuccess();
		assert learningSubject.getRequirements().getFirst().getStudentObjective().text().equals("R2");
	}

}
