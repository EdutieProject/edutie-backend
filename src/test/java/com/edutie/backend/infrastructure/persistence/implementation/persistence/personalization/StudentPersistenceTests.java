package com.edutie.backend.infrastructure.persistence.implementation.persistence.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentPersistenceTests {
    @Autowired
    LearningResultPersistence learningResultPersistence;
    @Autowired
    StudentPersistence studentPersistence;
    private final UserId userId = new UserId();
    @Test
    public void learningHistoryTest() {
        Student student = Student.create(userId);
        studentPersistence.save(student).throwIfFailure();

        LearningResult learningResult = LearningResult.create(student, null, new Feedback("", FeedbackType.NEUTRAL));
        learningResultPersistence.save(learningResult).throwIfFailure();

        Student studentFetched = studentPersistence.getById(student.getId()).getValue();
        assert !studentFetched.getLearningHistory().isEmpty();
    }
}
