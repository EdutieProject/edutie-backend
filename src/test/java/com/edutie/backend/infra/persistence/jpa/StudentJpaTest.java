package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.common.studenttraits.Intelligence;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.entites.IntelligenceLearningParameter;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentJpaTest {
    private static final UserId testUser = new UserId();
    private static Student student;
    private static StudentRepository studentRepository;

    @BeforeAll
    public static void testSetup() {
        student = Student.create(testUser);
        studentRepository.save(student);
    }

    @Test
    public void learningParamAdaptationTest() {
        student.adaptLearningParameters(IntelligenceLearningParameter.class, Intelligence.LOGICAL, 50);
        studentRepository.save(student);

        var fetched = studentRepository.findById(student.getId()).orElseThrow();
        assertFalse(student.getAllLearningParameters().isEmpty());
        assertTrue(
                fetched.getAllLearningParameters().contains(
                        student.getAllLearningParameters().stream().findFirst().get())
        );
    }

}
