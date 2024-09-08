package com.edutie.backend.domainservice.personalization.learningresource;

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
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.mocks.LearningMocks;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LearningResourceGenerationSchemaServiceTests {
	final LearningResourceGenerationSchemaService learningResourceGenerationSchemaService = new LearningResourceGenerationSchemaServiceImplementation(LearningMocks.knowledgeMapServiceMock());
	private final UserId userId = new UserId();
	private final Educator educator = Educator.create(userId, Administrator.create(userId));

	@Test
	public void learningResourceGenerationServiceNoLearningHistoryTest() {
		Student student = Student.create(userId);

		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.setName("Integration by parts");
		learningRequirement.setDescription(PromptFragment.of("Here would go the description of integration by parts"));
		learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
		learningRequirement.appendSubRequirement("Calculating derivatives and antiderivatives of ingredient functions");
		learningRequirement.appendSubRequirement("Proper formula usage");
		learningRequirement.appendSubRequirement("3rd sub req nfgoiufguoeoeaofsoefe");

		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(PromptFragment.of("Theory description"), PromptFragment.of("Exercise description"), Set.of(learningRequirement));
		learningResourceDefinition.setTheorySummaryAdditionalDescription(PromptFragment.of("Theory summary additional desc"));
		learningResourceDefinition.setHintsAdditionalDescription(PromptFragment.of("Hints additional desc"));

		LearningResourceGenerationSchema generationSchema = learningResourceGenerationSchemaService.createSchema(learningResourceDefinition, student).getValue();

		assertFalse(generationSchema.getProblemDescriptors().isEmpty());
		assertEquals(1, generationSchema.getProblemDescriptors().getFirst().getQualifiedSubRequirementOrdinal());
		assertTrue(generationSchema.getProblemDescriptors().getFirst().getPersonalizationRules().isEmpty());
	}

	@Test
	public void learningResourceGenerationServiceWithLearningHistoryTest() {
		Student student = Student.create(userId);

		LearningRequirement primaryLearningRequirement = LearningRequirement.create(educator);
		primaryLearningRequirement.setName("Integration by parts");
		primaryLearningRequirement.setDescription(PromptFragment.of("Here would go the description of integration by parts"));
		primaryLearningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId(UUID.fromString("4e92752a-5ef8-420e-ba45-260b6b7af5fe")));
		primaryLearningRequirement.appendSubRequirement("Calculating derivatives and antiderivatives of ingredient functions");
		primaryLearningRequirement.appendSubRequirement("Proper formula usage");
		primaryLearningRequirement.appendSubRequirement("3rd sub req nfgoiufguoeoeaofsoefe");

		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(PromptFragment.of("1"), PromptFragment.of("2"), Set.of(primaryLearningRequirement));
		LearningResource learningResource = LearningResource.create(LearningResourceGenerationSchema.create(learningResourceDefinition, student), Activity.create("", Set.of()), Theory.create("", ""), Set.of());
		SolutionSubmission solutionSubmission = SolutionSubmission.create(student, learningResource, "Thats my report!", 0);
		LearningResult learningResult = LearningResult.create(student, solutionSubmission, new Feedback("HEllo, World!", FeedbackType.NEUTRAL));
		learningResult.addAssessment(Assessment.create(primaryLearningRequirement.getId(), new Grade(5)));

		student.getLearningHistory().add(learningResult);

		LearningRequirement learningRequirement = LearningRequirement.create(educator);
		learningRequirement.setName("U substitution integration");
		learningRequirement.setDescription(PromptFragment.of("Req1"));
		learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
		learningRequirement.appendSubRequirement("SUBREQ1");
		learningRequirement.appendSubRequirement("SUBREQ2");
		learningRequirement.appendSubRequirement("SUBREQ3");

		LearningResourceDefinition learningResourceDefinition2 = LearningResourceDefinition.create(PromptFragment.of("Theory description"), PromptFragment.of("Exercise description"), Set.of(learningRequirement));
		learningResourceDefinition2.setTheorySummaryAdditionalDescription(PromptFragment.of("Theory summary additional desc"));
		learningResourceDefinition2.setHintsAdditionalDescription(PromptFragment.of("Hints additional desc"));

		LearningResourceGenerationSchema generationSchema = learningResourceGenerationSchemaService.createSchema(learningResourceDefinition, student).getValue();

		try {
			System.out.println(new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new JavaTimeModule()).writeValueAsString(generationSchema));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		assertFalse(generationSchema.getProblemDescriptors().getFirst().getPersonalizationRules().isEmpty());
		// 4 value is from mock
		assertEquals(4, generationSchema.getProblemDescriptors().getFirst().getPersonalizationRules().getFirst().getKnowledgeCorrelationFactor());
		assertEquals(3, generationSchema.getProblemDescriptors().getFirst().getQualifiedSubRequirementOrdinal());

	}
}
