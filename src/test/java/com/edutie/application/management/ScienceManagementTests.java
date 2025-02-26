package com.edutie.application.management;

import com.edutie.application.management.science.CreateScienceCommandHandler;
import com.edutie.application.management.science.commands.CreateScienceCommand;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.Result;
import validation.WrapperResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class ScienceManagementTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(userId);
	private final Educator educator = Educator.create(userId, administrator);
	@Autowired
	CreateScienceCommandHandler createScienceCommandHandler;
	@Autowired
	EducatorPersistence educatorPersistence;
	@Autowired
	AdministratorPersistence administratorPersistence;

	@BeforeEach
	public void testSetup() {
		Result result2 = administratorPersistence.save(administrator);
		System.out.println("ADMIN SAVE RESULT " + result2.getError());
		Result result = educatorPersistence.save(educator);
		System.out.println("EDUCATOR SAVE RESULT " + result.getError());
	}

	@Test
	public void createScienceTest() {
		CreateScienceCommand command = new CreateScienceCommand().educatorUserId(userId).scienceName("Mathematics");

		WrapperResult<Science> scienceWrapperResult = createScienceCommandHandler.handle(command);

		assert scienceWrapperResult.isSuccess();
		assert scienceWrapperResult.getValue().getName().equals("Mathematics");
	}
}
