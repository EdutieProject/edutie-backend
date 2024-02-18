package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.common.studenttraits.Intelligence;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.entites.IntelligenceLearningParameter;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.AbilityLearningParamRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.IntelligenceLearningParamRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
public class StudentJpaTest {
    private static final UserId testUser = new UserId();
    private static Student student;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private IntelligenceLearningParamRepository intelligenceLearningParamRepository;
    @Autowired
    private AbilityLearningParamRepository abilityLearningParamRepository;

    @BeforeAll
    public static void testSetup() {
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
