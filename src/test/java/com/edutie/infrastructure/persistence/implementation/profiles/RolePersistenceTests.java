package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class RolePersistenceTests {
	@Autowired
	private MockUser mockUser;
	@Autowired
	AdministratorPersistence administratorPersistence;
	@Autowired
	private EducatorPersistence educatorPersistence;

	@BeforeEach
	public void testSetup() {
		mockUser.saveToPersistence();
	}

	@Test
	public void getByAuthorizedUserIdTest() {
		UserId newUserId = new UserId();
		Educator educator = Educator.create(newUserId, mockUser.getAdministratorProfile());
		assert educatorPersistence.save(educator).isSuccess();

		assert educatorPersistence.getByAuthorizedUserId(newUserId).equals(educator);
	}
}
