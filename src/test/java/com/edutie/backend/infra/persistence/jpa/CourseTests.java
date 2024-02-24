package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.creator.Creator;
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
    private Course course;
    private Creator creator;
    private Science science;


    @Autowired
    private CreatorRepository creatorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @BeforeEach
    public void testSetup() {
        creator = Creator.create(testUserId);
        creatorRepository.save(creator);
        science = Science.create(testUserId);
        course = Course.create(creator, science);
    }

    @Test
    public void testCourseCreation() {

        assertNotNull(course);
        assertEquals(creator, course.getCreator());
        assertEquals(science, course.getScience());

        scienceRepository.save(science);
        courseRepository.save(course);

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

        assertEquals("Java Programming", course.getName());
        assertEquals("Learn Java Programming from scratch", course.getDescription());
    }

    @Test
    public void testOneToManyRelationship() {

        Course course1 = Course.create(creator, science);
        Course course2 = Course.create(creator, science);

        scienceRepository.save(science);
        courseRepository.save(course);
        courseRepository.save(course1);
        courseRepository.save(course2);

        var fetched = courseRepository.findById(course.getId()).orElseThrow();
        var fetched1 = courseRepository.findById(course1.getId()).orElseThrow();
        var fetched2 = courseRepository.findById(course2.getId()).orElseThrow();

        assertEquals(fetched, course);
        assertEquals(fetched1, course1);
        assertEquals(fetched2, course2);
    }
}
