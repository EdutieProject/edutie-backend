package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import validation.WrapperResult;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class GetLearningResourceTests {
	private final UserId userId = new UserId();
	private final Student student = Student.create(userId);
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	@Autowired
	private AdministratorPersistence administratorPersistence;
	@Autowired
	private EducatorPersistence educatorPersistence;
	@Autowired
	private GetLearningResourceByIdQueryHandler getLearningResourceByIdQueryHandler;
	@Autowired
	private LearningResourcePersistence learningResourcePersistence;
	@Autowired
	private LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
	@Autowired
	private StudentPersistence studentPersistence;

	@BeforeEach
	public void testSetup() {
		administratorPersistence.save(administrator).throwIfFailure();
		studentPersistence.save(student).throwIfFailure();
		educatorPersistence.save(educator).throwIfFailure();
	}

	@Test
	public void getLearningResourceByIdTest() {
		LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(educator, PromptFragment.of(""), PromptFragment.of(""));
		learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

		LearningResource learningResource = LearningResource.create(
				LearningResourceGenerationSchema.create(learningResourceDefinition, student),
				Activity.create("", Set.of()),
				Theory.create("", ""),
				Set.of()
		);
		learningResourcePersistence.save(learningResource).throwIfFailure();

		GetLearningResourceByIdQuery query = new GetLearningResourceByIdQuery()
				.studentUserId(userId)
				.learningResourceId(learningResource.getId());

		WrapperResult<LearningResource> queryResult = getLearningResourceByIdQueryHandler.handle(query);

		assertTrue(queryResult.isSuccess());
		assertEquals(queryResult.getValue(), learningResource);
	}
}
