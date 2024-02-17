package com.edutie.backend.domain.learner;

import com.edutie.backend.domain.common.studenttraits.Ability;
import com.edutie.backend.domain.common.studenttraits.Intelligence;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.entites.AbilityLearningParameter;
import com.edutie.backend.domain.learner.student.entites.IntelligenceLearningParameter;
import com.edutie.backend.domain.learner.student.enums.SchoolType;
import com.edutie.backend.domain.learner.student.valueobjects.SchoolStage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentTests {

    @Test
    public void studentPropertiesCreationTest()
    {
        Student student = new Student();

        assertNull(student.getBirthdate());
        assertNull(student.getSchoolStage());
    }

    @Test
    public void studentSetBirthdateSuccessTest()
    {
        Student student = new Student();
        var result = student.setBirthdate(LocalDate.of(1410, 7, 15));
        System.out.println("CODE:" + result.getErrors().get(0).code());
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    public void studentSetBirthDateFailureTest()
    {
        Student student = new Student();
        Assertions.assertTrue(student.setBirthdate(LocalDate.of(2004, 7,18)).isSuccess());
    }

    @Test
    public void studentSetSchoolStageFailureTest()
    {
        Student student = new Student();
        SchoolStage invalidSchoolStage = new SchoolStage(SchoolType.HIGH_SCHOOL, 10);

        Assertions.assertFalse(student.setSchoolStage(invalidSchoolStage).isSuccess());
    }

    @Test
    public void studentSetSchoolStageSuccessTest()
    {
        Student student = new Student();
        SchoolStage validSchoolStage = new SchoolStage(SchoolType.TECHNICAL_HIGH_SCHOOL, 5);

        Assertions.assertTrue(student.setSchoolStage(validSchoolStage).isSuccess());
    }

    @Test
    public void changeSchoolStageFailureTest() {
        Student student = new Student();
        student.setSchoolStage(
                new SchoolStage(SchoolType.HIGH_SCHOOL, 3)
        );
        Assertions.assertFalse(student.changeSchoolStage(10).isSuccess());
    }

    @Test
    public void changeSchoolStagePassValidationTest() {
        Student student = new Student();
        student.setSchoolStage(
                new SchoolStage(SchoolType.HIGH_SCHOOL, 1)
        );
        Assertions.assertTrue(student.changeSchoolStage(3).isSuccess());
    }

    @Test
    public void learningParamsInitializationTests()
    {
        Student student = new Student();
        assertTrue(student.getAllLearningParameters().isEmpty());
    }

    @Test
    public void noLearningParameterTest()
    {
        Student student = new Student();
        assertFalse(student.getLearningParameter(AbilityLearningParameter.class, Ability.ADAPTABILITY).isPresent());
    }

    @Test
    public void learningParametersAdaptationTest()
    {
        Student student = new Student();
        student.adaptLearningParameters(AbilityLearningParameter.class, Ability.CRITICAL_THINKING, 10.0);
        assertFalse(student.getAllLearningParameters().isEmpty());
        assertTrue(student.getLearningParameter(AbilityLearningParameter.class, Ability.CRITICAL_THINKING).isPresent());
        Assertions.assertEquals(10.0, student.getLearningParameter(AbilityLearningParameter.class, Ability.CRITICAL_THINKING).get().getValue());

        student.adaptLearningParameters(AbilityLearningParameter.class, Ability.CRITICAL_THINKING, 10.0);
        Assertions.assertEquals(20.0, student.getLearningParameter(AbilityLearningParameter.class, Ability.CRITICAL_THINKING).get().getValue());
    }

    @Test
    public void getLearningParameterValueTest()
    {
        Student student = new Student();
        student.adaptLearningParameters(IntelligenceLearningParameter.class, Intelligence.LOGICAL, 55.0);
        assertEquals(55.0, student.getLearningParameterValue(IntelligenceLearningParameter.class, Intelligence.LOGICAL));
    }


}
