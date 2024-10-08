package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;

import java.util.Set;

@SpringBootTest
public class StudentTests {
	private final UserId userId = new UserId();
	private final Educator educator = Educator.create(userId, Administrator.create(userId));

	@Test
	public void getLearningHistoryByKnowledgeSubjectTest() {
		Student student = Student.create(userId);

		KnowledgeSubjectId knowledgeSubjectId = new KnowledgeSubjectId();

		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.setKnowledgeSubjectId(knowledgeSubjectId);
		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(educator, PromptFragment.of("dsa"), PromptFragment.of("dada"));
		LearningResource learningResource = LearningResource.create(LearningResourceGenerationSchema.create(learningResourceDefinition, student), Activity.create("", Set.of()), Theory.create("", ""), Set.of());

		LearningResult learningResult = LearningResult.create(student, SolutionSubmission.create(student, learningResource, "My report", 0), new Feedback("My feedback", FeedbackType.NEUTRAL));
		student.getLearningHistory().add(learningResult);

		assert !student.getLearningHistoryByKnowledgeSubject(knowledgeSubjectId).isEmpty();
	}

	@Test
	public void getLearningHistoryByKnowledgeSubjectEmptyVariantTest() {
		Student student = Student.create(userId);

		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(educator, PromptFragment.of("dsa"), PromptFragment.of("dada"));
		LearningResource learningResource = LearningResource.create(LearningResourceGenerationSchema.create(learningResourceDefinition, student), Activity.create("", Set.of()), Theory.create("", ""), Set.of());
		LearningResult learningResult = LearningResult.create(student, SolutionSubmission.create(student, learningResource, "My report", 0), new Feedback("My feedback", FeedbackType.NEUTRAL));
		student.getLearningHistory().add(learningResult);

		assert student.getLearningHistoryByKnowledgeSubject(new KnowledgeSubjectId()).isEmpty();
	}
}
