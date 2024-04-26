package com.edutie.backend.infra.persistence;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.Result;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CoursePersistenceTests {
    @Autowired
    private CoursePersistence coursePersistence;
    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private ScienceRepository scienceRepository;
    private final UserId userId = new UserId();
    private final Educator educator = Educator.create(userId, new AdminId());
    private final Science science = Science.create(userId);
    private Course course;

    @BeforeEach
    public void testSetup() {
        educatorRepository.save(educator);
        scienceRepository.save(science);
        course = Course.create(educator, science);
        saveAndAssert();
    }

    public void saveAndAssert() {
        Result res = coursePersistence.save(course);
        if (res.isFailure()) {
            System.out.println(res.getError());
            throw new AssertionError();
        }
    }

    @Test
    public void gndGetByScienceTest() {
        List<Course> courses = coursePersistence.getAllOfScienceId(science.getId()).getValue();
        assertTrue(courses.contains(course));
    }

    @Test
    public void getByEducatorTest() {
        List<Course> courses = coursePersistence.getAllOfEducatorId(educator.getId()).getValue();
        assertTrue(courses.contains(course));
    }

    @Test
    public void getAllAccessibleOfScienceId() {
        course.setAccessible(true);
        saveAndAssert();
        List<Course> courses = coursePersistence.getAllAccessibleOfScienceId(science.getId()).getValue();
        assertTrue(courses.contains(course));
    }

}
