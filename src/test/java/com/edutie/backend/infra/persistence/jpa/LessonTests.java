package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CourseRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LessonRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@NoArgsConstructor
public class LessonTests {
    private final UserId testUserId = new UserId();
    private final AdminId adminId = new AdminId();
    private Educator educator;
    private Course course;
    private Lesson lesson;

    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;

    @BeforeEach
    public void testSetup() {
        educator = Educator.create(testUserId, adminId);
        educatorRepository.save(educator);

        Science science = Science.create(testUserId);
        scienceRepository.save(science);

        course = Course.create(educator, science).getValue();
        courseRepository.save(course);

        lesson = Lesson.create(educator, course).getValue();
        lessonRepository.save(lesson);
    }

    @Test
    public void testCreate() {
        lesson = Lesson.create(educator, course).getValue();
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

        Lesson lesson1 = Lesson.create(educator, course).getValue();
        Lesson lesson2 = Lesson.create(educator, course).getValue();

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
    @Transactional
    public void testAddNextElement() {
        Lesson lesson1 = Lesson.create(educator, course).getValue();
        Lesson lesson2 = Lesson.create(educator, course).getValue();
        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        lesson.addNextElement(lesson1);
        lessonRepository.save(lesson);

        var fetch1 = lessonRepository.findById(lesson.getId()).orElseThrow();
        assertEquals(fetch1.getNextElements().stream().findFirst().orElseThrow(), lesson1);

        lesson.addNextElement(lesson2);
        lessonRepository.save(lesson);

        var fetch2 = lessonRepository.findById(lesson.getId()).orElseThrow();
        assertEquals(fetch2.getNextElements().stream().skip(1).findFirst().orElseThrow(), lesson2);
    }

    @Test
    @Transactional
    public void testSetPreviousElement(){
        Lesson lesson1 = Lesson.create(educator, course).getValue();
        Lesson lesson2 = Lesson.create(educator, course).getValue();
        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        lesson.setPreviousElement(lesson1);
        lessonRepository.save(lesson);

        var fetch1 = lessonRepository.findById(lesson.getId()).orElseThrow();
        assertEquals(fetch1.getPreviousElement(), lesson1);

        lesson.setPreviousElement(lesson2);
        lessonRepository.save(lesson);

        var fetch2 = lessonRepository.findById(lesson.getId()).orElseThrow();
        assertEquals(fetch2.getPreviousElement(), lesson2);

    }

    @Test
    public void testLessonCourseRelationShip(){
        Lesson fetchedLesson = lessonRepository.getReferenceById(lesson.getId());

        assertEquals(fetchedLesson.getId(), lesson.getId());
        assertEquals(lesson.getCourse(), course);
    }
}
