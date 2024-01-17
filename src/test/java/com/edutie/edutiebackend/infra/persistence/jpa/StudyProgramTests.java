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

    @Test
    public void courseCreateRetrieveTest()
    {
        Course course = new Course();
        course.setId(new CourseId());
        course.setName("Sample course");
        course.setDescription("Description text");
        courseRepository.save(course);

        assertTrue(courseRepository.findById(course.getId()).isPresent());
    }

    @Test
    public void scienceOnlyTest()
    {
        Science science = new Science("Math", "The princess of sciences");
        science.setId(new ScienceId());
        scienceRepository.save(science);

        assertTrue(scienceRepository.findById(science.getId()).isPresent());
    }

    @Test
    @Transactional(readOnly = true)
    public void courseCreateRetrieveTestWithScience()
    {
        Science science = new Science("Name", "Desc");
        science.setId(new ScienceId());
        scienceRepository.save(science);
        Course course = new Course();
        course.setName("Sample Course Name");
        course.setDescription("Boring description");
        course.setId(new CourseId());
        course.setScience(science);
        courseRepository.save(course);

        var retrievedCourse = courseRepository.getReferenceById(course.getId());
        assertEquals(retrievedCourse.getName(), course.getName());
        assertEquals(retrievedCourse.getScience(), science);
    }

    @Test
    @Transactional(readOnly = true)
    public void courseScienceRelationShipTest()
    {
        Science science = new Science();
        science.setName("Math or something");
        science.setDescription("Science desc");
        science.setId(new ScienceId());
        scienceRepository.save(science);

        CourseId courseId = new CourseId();
        Course course = new Course();
        course.setId(courseId);
        course.setName("Course 2");
        course.setDescription("Second course description");
        course.setScience(science);
        courseRepository.save(course);

        var createdCourse = courseRepository.getReferenceById(courseId);
        assertNotNull(createdCourse);
        assertEquals(createdCourse.getScience(), science);
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

        assertTrue(lessonRepository.findById(lesson.getId()).isPresent());
    }

    @Test
    @Transactional(readOnly = true)
    public void lessonCourseRelationShipTest()
    {
        Course course = new Course();
        course.setId(new CourseId());
        course.setName("Course");
        course.setDescription("Course description");
        courseRepository.save(course);

        Lesson lesson = new Lesson();
        lesson.setId(new LessonId());
        lesson.setCourse(course);
        lesson.setName("Lesson");
        lesson.setDescription("Lesson description");
        lessonRepository.save(lesson);

        Lesson fetchedLesson = lessonRepository.getReferenceById(lesson.getId());

        assertEquals(fetchedLesson.getId(), lesson.getId());
        assertEquals(lesson.getCourse(), course);
    }

}
