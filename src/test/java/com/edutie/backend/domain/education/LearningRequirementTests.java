package com.edutie.backend.domain.education;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class LearningRequirementTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(new UserId());
	private final Educator educator = Educator.create(userId, administrator);

	@BeforeEach
	public void testSetup() {
		educator.setType(EducatorType.ADMINISTRATOR);
	}

	@Test
	public void subRequirementsAppendTest() {
		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.appendSubRequirement("R1", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("R2", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("R3", PromptFragment.of(""));

		assert learningRequirement.getElementalRequirements().get(0).getObjectiveText().text().equals("R1");
		assert learningRequirement.getElementalRequirements().get(2).getObjectiveText().text().equals("R3");
	}

	@Test
	public void subRequirementsInsertTest() {
		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.appendSubRequirement("R1", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("R2", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("R3", PromptFragment.of(""));
		assert learningRequirement.insertSubRequirement("Hello!", PromptFragment.of(""), 1).isSuccess();

		assert learningRequirement.getElementalRequirements().get(1).getObjectiveText().text().equals("Hello!");
	}

	@Test
	public void subRequirementDeleteTest() {
		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.appendSubRequirement("R1", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("R2", PromptFragment.of(""));
		learningRequirement.appendSubRequirement("R3", PromptFragment.of(""));
		assert learningRequirement.getElementalRequirements().size() == 3;

		assert learningRequirement.removeSubRequirement(0).isSuccess();
		assert learningRequirement.getElementalRequirements().getFirst().getObjectiveText().text().equals("R2");
	}

}
