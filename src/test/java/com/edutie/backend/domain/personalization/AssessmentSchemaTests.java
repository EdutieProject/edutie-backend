package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;

import java.util.Set;

@SpringBootTest
public class AssessmentSchemaTests {
	private final UserId userId = new UserId();
	private final Student student = Student.create(userId);
	private final LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(PromptFragment.of(""), PromptFragment.of(""), Set.of());

	@Test
	public void createAssessmentSchemaTest() {
		LearningResource learningResource = LearningResource.create(LearningResourceGenerationSchema.create(learningResourceDefinition, student), Activity.create("", Set.of()), Theory.create("", ""), Set.of());

		SolutionSubmission solutionSubmission = SolutionSubmission.create(student, learningResource, "This is my report", 0);

		AssessmentSchema assessmentSchema = AssessmentSchema.create(student, solutionSubmission, learningResource);
		//TODO: test problem descriptors once #135 is resolved

	}
}
