package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CourseRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CreatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LessonRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@NoArgsConstructor
public class LessonTests {
    private final UserId testUserId = new UserId();
    private Creator creator;
    private Course course;
    private Lesson lesson;

    @Autowired
    private CreatorRepository creatorRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @BeforeEach
    public void testSetup() {
        creator = Creator.create(testUserId);
        creatorRepository.save(creator);

        Science science = Science.create(testUserId);
        scienceRepository.save(science);

        course = Course.create(creator, science);
        courseRepository.save(course);

        lesson = Lesson.create(creator, course);
        lessonRepository.save(lesson);
    }

    @Test
    public void testCreate() {
        lesson = Lesson.create(creator, course);
        lessonRepository.save(lesson);

        assertEquals(lessonRepository.findById(lesson.getId()).orElseThrow(), lesson);
    }

    @Test
    public void testLessonNameAndDescription() {
        lesson.setName("TestName");
        lesson.setDescription("TestDescription");

        assertEquals("TestName", lesson.getName());
        assertEquals("TestDescription", lesson.getDescription());
    }

    @Test
    public void testOneToManyRelationship() {

        Lesson lesson1 = Lesson.create(creator, course);
        Lesson lesson2 = Lesson.create(creator, course);

        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        var fetched = lessonRepository.findById(lesson.getId()).orElseThrow();
        var fetched1 = lessonRepository.findById(lesson1.getId()).orElseThrow();
        var fetched2 = lessonRepository.findById(lesson2.getId()).orElseThrow();

        assertEquals(fetched, lesson);
        assertEquals(fetched1, lesson1);
        assertEquals(fetched2, lesson2);
    }


    @Test
    public void testAddNextElement() {
        Lesson lesson1 = Lesson.create(creator, course);
        assertNotNull(lesson1);
        assertNotNull(lesson1.getId());

        lessonRepository.save(lesson1);
        lesson.addNextElement(lesson1);

        var fetech = lessonRepository.findById(lesson1.getId()).orElseThrow();
        assertEquals(fetech.getCourse(), course);
    }
}
