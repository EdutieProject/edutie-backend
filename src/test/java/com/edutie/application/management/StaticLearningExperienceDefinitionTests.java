package com.edutie.application.management;

import com.edutie.application.management.learningresourcedefinition.CreateLearningResourceDefinitionCommandHandler;
import com.edutie.application.management.learningresourcedefinition.commands.CreateLearningResourceDefinitionCommand;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.WrapperResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class StaticLearningExperienceDefinitionTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	@Autowired
	private EducatorPersistence educatorPersistence;
	@Autowired
	private AdministratorPersistence administratorPersistence;
	@Autowired
	private CreateLearningResourceDefinitionCommandHandler createLearningResourceDefinitionCommandHandler;

	@BeforeEach
	public void testSetup() {
		administratorPersistence.save(administrator).throwIfFailure();
		educatorPersistence.save(educator).throwIfFailure();
	}

	@Test
	public void createLearningResourceDefinitionTest() {
		CreateLearningResourceDefinitionCommand command = new CreateLearningResourceDefinitionCommand()
				.theoryDescription("LRD theory descriptor")
				.exerciseDescription("LRD exercise descriptor")
				.educatorUserId(userId);

		WrapperResult<StaticLearningResourceDefinition> wrapperResult = createLearningResourceDefinitionCommandHandler.handle(command);

		assert wrapperResult.isSuccess();
		StaticLearningResourceDefinition staticLearningResourceDefinition = wrapperResult.getValue();
		assert staticLearningResourceDefinition.getLearningRequirements().isEmpty();
		assert staticLearningResourceDefinition.getActivityDetails().getExerciseDescription().text().equals("LRD exercise descriptor");
	}
}
