package com.edutie.edutiebackend.domain;

import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.core.student.exceptions.InvalidBirthDateException;
import com.edutie.edutiebackend.domain.core.student.exceptions.InvalidSchoolStageException;
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
    public void studentSetBirthdateThrowValidationTest()
    {
        Student student = new Student();

        assertThrows(
                InvalidBirthDateException.class,
                ()->student.setBirthdate(LocalDate.MIN)
        );
    }

    @Test
    public void studentSetBirthDatePassValidationTest()
    {
        Student student = new Student();

        assertDoesNotThrow(
                ()->student.setBirthdate(LocalDate.of(2004, 7,18))
        );
    }

    @Test
    public void studentSetSchoolStageThrowValidationTest()
    {
        Student student = new Student();
        SchoolStage invalidSchoolStage = new SchoolStage(SchoolType.HighSchool, 10);

        assertThrows(
                InvalidSchoolStageException.class,
                ()->student.setSchoolStage(invalidSchoolStage)
        );
    }

    @Test
    public void studentSetSchoolStagePassValidationTest()
    {
        Student student = new Student();
        SchoolStage validSchoolStage = new SchoolStage(SchoolType.TechnicalHighSchool, 5);

        assertDoesNotThrow(
                ()->student.setSchoolStage(validSchoolStage)
        );
    }

    @Test
    public void changeSchoolStageThrowValidationTest() throws InvalidSchoolStageException {
        Student student = new Student();

        student.setSchoolStage(
                new SchoolStage(SchoolType.HighSchool, 3)
        );
        assertThrows(
                InvalidSchoolStageException.class,
                () -> student.changeSchoolStage(10)
        );
    }

    @Test
    public void changeSchoolStagePassValidationTest() throws InvalidSchoolStageException {
        Student student = new Student();
        student.setSchoolStage(
                new SchoolStage(SchoolType.HighSchool, 1)
        );
        assertDoesNotThrow(
                    () -> student.changeSchoolStage(1)
        );
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
                RuntimeException.class,
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
