package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.Set;

@SpringBootTest
public class StudentPersistenceTests {
	@Autowired
	MockUser mockUser;
	@Autowired
	LearningResultPersistence learningResultPersistence;
	@Autowired
	StudentPersistence studentPersistence;

	@BeforeEach
	public void testSetup() {
		mockUser.saveToPersistence();
	}

	@Test
	public void learningHistoryTest() {
		LearningResult learningResult = LearningResult.create(
				null,
				new Feedback("", FeedbackType.NEUTRAL),
				Set.of()
		);
		learningResultPersistence.save(learningResult).throwIfFailure();

		Student studentFetched = studentPersistence.getById(mockUser.getStudentProfile().getId()).getValue();
		assert !studentFetched.getLearningHistory().isEmpty();
	}
}
