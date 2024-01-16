package com.edutie.edutiebackend.infra.persistence.jpa;

import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.lesson.Lesson;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.core.science.Science;
import com.edutie.edutiebackend.domain.core.science.identities.ScienceId;
import com.edutie.edutiebackend.infra.persistence.jpa.repositories.CourseRepository;
import com.edutie.edutiebackend.infra.persistence.jpa.repositories.LessonRepository;
import com.edutie.edutiebackend.infra.persistence.jpa.repositories.ScienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class encompasses database tests related to
 * relationships of main study program structures, such as
 * lessons and courses.
 */
@SpringBootTest
public class StudyProgramTests {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;
    @Autowired
    private LessonRepository lessonRepository;

    /**
     * This test does not pass when fetch type is EAGER
     */
    @Test
    public void courseCreateRetrieveTest()
    {
        Course course = new Course();
        course.setId(new CourseId());
        course.setName("Sample course");
        course.setDescription("");
        courseRepository.save(course);

        assertTrue(courseRepository.findById(course.getId()).isPresent());
    }

    @Test
    public void courseCreateRetrieveTestWithScience()
    {
        Science science = new Science("Name", "Desc");
        science.setId(new ScienceId());
        scienceRepository.save(science);
        Course course = new Course();
        course.setName("Sample Course Name");
        course.setDescription("Boring description");
        course.setId(new CourseId());
        course.setScienceId(science.getId());
        courseRepository.save(course);

        var retrievedCourse = courseRepository.findById(course.getId());
        assertTrue(retrievedCourse.isPresent());
        assertEquals(retrievedCourse.orElse(new Course()).getName(), course.getName());
    }

    @Test
    @Transactional(readOnly = true)
    public void courseScienceRelationShipTest()
    {
        Science science = new Science();
        science.setName("Math or something");
        science.setDescription("Science desc");
        var scienceId = new ScienceId();
        science.setId(scienceId);
        scienceRepository.save(science);

        CourseId courseId = new CourseId();
        Course course = new Course();
        course.setId(courseId);
        course.setName("Course 2");
        course.setDescription("Second course description");
        course.setScienceId(scienceId);
        courseRepository.save(course);

        var createdCourse = courseRepository.findById(courseId);
        assertTrue(createdCourse.isPresent());
        assertEquals(createdCourse.get().getScienceId(), scienceId);
        assertEquals(createdCourse.get().getScience(), science);
    }

    @Test
    public void lessonCreateRetrieveTest()
    {
        Lesson lesson = new Lesson();
        var lessonId = new LessonId();
        lesson.setId(lessonId);
        lesson.setName("The first lesson");
        lesson.setDescription("First lesson's description");
        lessonRepository.save(lesson);
        assertEquals(lessonRepository.findAll().get(0).getId(), lessonId);
        assertTrue(lessonRepository.findById(lesson.getId()).isPresent());
    }

    @Test
    public void lessonCourseRelationShipTest()
    {
        Course course = new Course();
        course.setId(new CourseId());
        course.setName("Course");
        course.setDescription("Course description");
        courseRepository.save(course);

        Lesson lesson = new Lesson();
        lesson.setId(new LessonId());
        lesson.setCourseId(course.getId());
        lesson.setName("Lesson");
        lesson.setDescription("Lesson description");
        lessonRepository.save(lesson);

        List<Lesson> lessons = lessonRepository.findAll();

        assertFalse(lessons.isEmpty());
        assertEquals(lessons.get(0).getCourseId(), course.getId());
    }

}
