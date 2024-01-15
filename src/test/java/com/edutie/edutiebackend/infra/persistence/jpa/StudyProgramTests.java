package com.edutie.edutiebackend.infra.persistence.jpa;

import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.science.Science;
import com.edutie.edutiebackend.domain.core.science.identities.ScienceId;
import com.edutie.edutiebackend.infra.persistence.jpa.repositories.CourseRepository;
import com.edutie.edutiebackend.infra.persistence.jpa.repositories.ScienceRepository;
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

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @Test
    public void courseSaveTest()
    {
        Course course = new Course();
        course.setName("Sample Course Name");
        course.setDescription("Boring description");
        course.setId(new CourseId());
        var saved = courseRepository.save(course);

        assertEquals(saved.getId(), course.getId());
    }

    @Test
    public void courseScienceRelationShipTest()
    {
        Science science = new Science();
        science.setName("Math or something");
        science.setDescription("Science desc");
        var scienceId = new ScienceId();
        science.setId(scienceId);
        scienceRepository.save(science);

        Course course = new Course();
        course.setId(new CourseId());
        course.setName("Course 2");
        course.setDescription("Second course description");
        course.setScienceId(scienceId);
        var savedCourse = courseRepository.save(course);

        assertEquals(savedCourse.getScienceId(), scienceId);
        assertEquals(savedCourse.getScience(), science);
    }

}
