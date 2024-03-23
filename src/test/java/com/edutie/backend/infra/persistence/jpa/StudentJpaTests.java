package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.common.studenttraits.Intelligence;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.entites.IntelligenceLearningParameter;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.IntelligenceLearningParamRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RequiredArgsConstructor
public class StudentJpaTests {
    private final UserId testUser = new UserId();private Student student;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private IntelligenceLearningParamRepository intelligenceLearningParamRepository;

    @BeforeEach
    public void testSetup() {
        student = Student.create(testUser);
    }

    @Test
    public void learningParamAdaptationTest() {
        student.adaptLearningParameters(IntelligenceLearningParameter.class, Intelligence.LOGICAL, 50);
        intelligenceLearningParamRepository.save(student.getIntelligenceLearningParameters().stream().findFirst().orElseThrow());

        studentRepository.save(student);

        var fetched = studentRepository.findById(student.getId()).orElseThrow();
        assertFalse(student.getAllLearningParameters().isEmpty());
        assertTrue(
                fetched.getAllLearningParameters().contains(
                        student.getAllLearningParameters().stream().findFirst().get())
        );
    }
}
