package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssessmentSchemaTests {
	private final UserId userId = new UserId();
	private final Student student = Student.create(userId);
	private final Educator educator = Educator.create(userId, Administrator.create(userId));

	@Test
	public void createAssessmentSchemaTest() {
        fail(); //TODO: resolve tests. issue #139 + #146
	}
}
