package com.edutie.edutiebackend.infra.persistence.jpa;

import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.lesson.Lesson;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.core.science.Science;
import com.edutie.edutiebackend.domain.core.science.identities.ScienceId;
import com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories.CourseRepository;
import com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories.LessonRepository;
import com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class encompasses database tests related to
 * relationships of main study program structures, such as
 * lessons and courses.
 */
@SpringBootTest
public class StudyProgramTests {

    UserId mockUser = new UserId();

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
        course.setCreatedBy(mockUser);
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
    public void courseCreateRetrieveWithScienceTest()
    {
        Science science = new Science("Name", "Desc");
        science.setId(new ScienceId());
        scienceRepository.save(science);
        Course course = new Course();
        course.setName("Sample Course Name");
        course.setDescription("Boring description");
        course.setId(new CourseId());
        course.setScience(science);
        course.setCreatedBy(mockUser);
        courseRepository.save(course);

        var retrievedCourse = courseRepository.findById(course.getId()).orElse(new Course());
        assertEquals(retrievedCourse.getName(), course.getName());
        assertEquals(retrievedCourse.getScience(), science);
    }

    @Test
    public void courseScienceRelationshipByIdTest()
    {
        Science science = new Science("Math", "Desc");
        science.setId(new ScienceId());
        scienceRepository.save(science);

        Course course = new Course();
        course.setId(new CourseId());
        course.setCreatedBy(mockUser);
        course.setName("ABC");
        course.setDescription("DEF");
        course.setScience(science);
        courseRepository.save(course);

        var fetchedCourse = courseRepository.findById(course.getId()).orElse(new Course());
        assertEquals(fetchedCourse.getScience(), science);
    }

    @Test
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
        course.setCreatedBy(mockUser);
        course.setName("Course 2");
        course.setDescription("Second course description");
        course.setScience(science);
        courseRepository.save(course);

        var createdCourse = courseRepository.findById(courseId).orElse(new Course());
        assertNotNull(createdCourse);
        assertEquals(createdCourse.getScience(), science);
    }

    @Test
    public void lessonCreateRetrieveTest()
    {
        Lesson lesson = new Lesson();
        var lessonId = new LessonId();
        lesson.setId(lessonId);
        lesson.setCreatedBy(mockUser);
        lesson.setName("The first lesson");
        lesson.setDescription("First lesson's description");
        lessonRepository.save(lesson);

        assertTrue(lessonRepository.findById(lesson.getId()).isPresent());
    }

    @Test
    public void lessonCourseRelationShipTest()
    {
        Course course = new Course();
        course.setId(new CourseId());
        course.setCreatedBy(mockUser);
        course.setName("Course");
        course.setDescription("Course description");
        courseRepository.save(course);

        Lesson lesson = new Lesson();
        lesson.setId(new LessonId());
        lesson.setCreatedBy(mockUser);
        lesson.setCourse(course);
        lesson.setName("Lesson");
        lesson.setDescription("Lesson description");
        lessonRepository.save(lesson);

        Lesson fetchedLesson = lessonRepository.getReferenceById(lesson.getId());

        assertEquals(fetchedLesson.getId(), lesson.getId());
        assertEquals(lesson.getCourse(), course);
    }

    @Test
    public void lessonNavigationTest()
    {
        Course course = new Course();
        course.setId(new CourseId());
        course.setName("Course");
        course.setDescription("Course description");
        course.setCreatedBy(mockUser);
        courseRepository.save(course);

        Lesson lesson1 = new Lesson();
        lesson1.setId(new LessonId());
        lesson1.setCreatedBy(mockUser);
        lesson1.setName("Lesson One");
        lesson1.setCourse(course);
        lesson1.setDescription("Haha");

        Lesson lesson2 = new Lesson();
        lesson2.setCreatedBy(mockUser);
        lesson2.setId(new LessonId());
        lesson2.setCourse(course);
        lesson2.setName("Lesson Two");
        lesson2.setDescription("Alright");

        // NOTE: this is the only way of performing this without transactions.
        lesson2.setPreviousElement(lesson1);

        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        var fetchedLesson1 = lessonRepository.findById(lesson1.getId()).orElseThrow();
        var fetchedLesson2 = lessonRepository.findById(lesson2.getId()).orElseThrow();

        assertTrue(fetchedLesson1.getNextElements().contains(lesson2));
        assertEquals(fetchedLesson2.getPreviousElement(), fetchedLesson1);
    }

}
