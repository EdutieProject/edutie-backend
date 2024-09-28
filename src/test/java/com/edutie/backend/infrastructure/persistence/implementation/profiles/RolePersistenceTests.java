package com.edutie.backend.infrastructure.persistence.implementation.profiles;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class RolePersistenceTests {
	private final UserId userId = new UserId();
	private final Administrator administrator = Administrator.create(new UserId());
	@Autowired
	private EducatorPersistence educatorPersistence;

	@Test
	public void getByAuthorizedUserIdTest() {
		Educator educator = Educator.create(userId, administrator);
		assert educatorPersistence.save(educator).isSuccess();

		assert educatorPersistence.getByAuthorizedUserId(userId).equals(educator);
	}
}
