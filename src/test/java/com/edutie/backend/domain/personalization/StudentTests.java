package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class StudentTests {
	private final UserId userId = new UserId();
	private final Educator educator = Educator.create(userId, Administrator.create(userId));

	@Test
	public void getLearningHistoryByKnowledgeSubjectTest() {
		//TODO (?)
	}

	@Test
	public void getLearningHistoryByKnowledgeSubjectEmptyVariantTest() {
		//TODO (?)
	}
}
