package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.core.student.exceptions.TraitTrackerNotFoundException;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;
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
        System.out.println("CODE:" + result.getRuleErrors().get(0).getCode());
        assertFalse(
                result.isSuccess()
        );
    }

    @Test
    public void studentSetBirthDateFailureTest()
    {
        Student student = new Student();
        assertTrue(
                student.setBirthdate(LocalDate.of(2004, 7,18)).isSuccess()
        );
    }

    @Test
    public void studentSetSchoolStageFailureTest()
    {
        Student student = new Student();
        SchoolStage invalidSchoolStage = new SchoolStage(SchoolType.HIGH_SCHOOL, 10);

        assertFalse(student.setSchoolStage(invalidSchoolStage).isSuccess());
    }

    @Test
    public void studentSetSchoolStageSuccessTest()
    {
        Student student = new Student();
        SchoolStage validSchoolStage = new SchoolStage(SchoolType.TECHNICAL_HIGH_SCHOOL, 5);

        assertTrue(student.setSchoolStage(validSchoolStage).isSuccess());
    }

    @Test
    public void changeSchoolStageFailureTest() {
        Student student = new Student();
        student.setSchoolStage(
                new SchoolStage(SchoolType.HIGH_SCHOOL, 3)
        );
        assertFalse(student.changeSchoolStage(10).isSuccess());
    }

    @Test
    public void changeSchoolStagePassValidationTest() {
        Student student = new Student();
        student.setSchoolStage(
                new SchoolStage(SchoolType.HIGH_SCHOOL, 1)
        );
        assertTrue(student.changeSchoolStage(3).isSuccess());
    }

    @Test
    public void LearningParamsInitializationTests()
    {
        Student student = new Student();
        var trackerInitValue = student.getLearningParameter(Intelligence.class, Intelligence.INTERPERSONAL);
        assertEquals(0.0, trackerInitValue);
    }

    @Test
    public void NoLearningParameterThrowTest()
    {
        Student student = new Student();
        enum Hello { WORLD, UNIVERSE }
        assertThrows(
                TraitTrackerNotFoundException.class,
                ()->student.getLearningParameter(Hello.class, Hello.UNIVERSE)
        );
    }

    @Test
    public void learningParametersAdaptationTest()
    {
        Student student = new Student();
        student.adaptLearningParameters(Ability.class, Ability.ADAPTABILITY, 10.0);
        var parameterValue = student.getLearningParameter(Ability.class, Ability.ADAPTABILITY);
        assertEquals(10.0, parameterValue);
    }

}
