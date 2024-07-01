package com.edutie.backend.infrastructure.persistence.implementation.persistence.studyprogram;

import com.edutie.backend.domain.administration.administrator.identities.AdministratorId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CourseRepository;
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

public class LessonPersistenceTests {
    @Autowired
    LessonPersistence lessonPersistence;
    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;
    private final UserId userId = new UserId();
    private final Educator educator = Educator.create(userId, new AdministratorId());
    private final Science science = Science.create(userId);
    private final Course course = Course.create(educator, science);
    private Lesson lesson;

    @BeforeEach
    public void testSetup() {
        educatorRepository.save(educator);
        scienceRepository.save(science);
        courseRepository.save(course);
        lesson = Lesson.create(educator, course);
        saveAndAssert();
    }

    public void saveAndAssert() {
        Result res = lessonPersistence.save(lesson);
        if (res.isFailure()) {
            System.out.println(res.getError());
            throw new AssertionError();
        }
    }

    @Test
    public void getByCourse() {
        List<Lesson> lessons = lessonPersistence.getAllOfCourseId(course.getId()).getValue();
        assertTrue(lessons.contains(lesson));
    }

    @Test
    public void getByEducator() {
        List<Lesson> lessons = lessonPersistence.getAllOfEducatorId(educator.getId()).getValue();
        assertTrue(lessons.contains(lesson));
    }
}
