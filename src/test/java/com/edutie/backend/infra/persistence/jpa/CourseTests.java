package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
public class CourseTests {
    private final UserId testUserId = new UserId();
    private final AdminId adminId = new AdminId();
    private Course course;
    private Educator educator;
    private Science science;


    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @BeforeEach
    public void testSetup() {
        educator = Educator.create(testUserId, adminId);
        educatorRepository.save(educator);
        science = Science.create(testUserId);
        scienceRepository.save(science);
        course = Course.create(educator, science);
        courseRepository.save(course);
    }

    @Test
    public void testCourseCreation() {

        var fetched = courseRepository.findById(course.getId()).orElseThrow();
        assertEquals(fetched, course);
    }

    @Test
    public void testCourseAccessibility() {
        assertFalse(course.isAccessible());
    }

    @Test
    public void testCourseNameAndDescription() {
        course.setName("Java Programming");
        course.setDescription("Learn Java Programming from scratch");
        courseRepository.save(course);

        var fetch = courseRepository.findById(course.getId()).orElseThrow();
        assertEquals("Java Programming", fetch.getName());
        assertEquals("Learn Java Programming from scratch", fetch.getDescription());
    }

    @Test
    public void testOneToManyRelationship() {
        Science science1 = Science.create(testUserId);
        scienceRepository.save(science1);

        Course course1 = Course.create(educator, science);
        Course course2 = Course.create(educator, science1);

        courseRepository.save(course);
        courseRepository.save(course1);
        courseRepository.save(course2);

        var fetched = courseRepository.findById(course.getId()).orElseThrow();
        var fetched1 = courseRepository.findById(course1.getId()).orElseThrow();
        var fetched2 = courseRepository.findById(course2.getId()).orElseThrow();

        assertEquals(fetched, course);
        assertEquals(fetched1, course1);
        assertEquals(fetched2, course2);

        var fetchedCourse = courseRepository.findById(course.getId()).orElse(new Course());
        assertEquals(fetchedCourse.getScience(), science);

        var fetchedCourse1 = courseRepository.findById(course1.getId()).orElse(new Course());
        assertEquals(fetchedCourse1.getScience(), science);

        var fetchedCourse2 = courseRepository.findById(course2.getId()).orElse(new Course());
        assertEquals(fetchedCourse2.getScience(), science1);
    }

    @Test
    public void  testCourseScienceRelationshipById(){

        var fetchedCourse = courseRepository.findById(course.getId()).orElse(new Course());
        assertEquals(fetchedCourse.getScience(), science);

    }


}
