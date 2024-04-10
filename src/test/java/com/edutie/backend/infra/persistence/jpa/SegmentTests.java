package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lessonsegment.Segment;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.*;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@NoArgsConstructor
public class SegmentTests {
    private final UserId testUserId = new UserId();
    private final AdminId adminId = new AdminId();
    private Lesson lesson;
    private Segment segment;
    private Educator educator;


    @Autowired
    private EducatorRepository educatorRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScienceRepository scienceRepository;
    @Autowired
    private LessonSegmentRepository lessonSegmentRepository;

    @BeforeEach
    public void testSetup() {
        educator = Educator.create(testUserId, adminId);
        educatorRepository.save(educator);

        Science science = Science.create(testUserId);
        scienceRepository.save(science);

        Course course = Course.create(educator, science);
        courseRepository.save(course);

        lesson = Lesson.create(educator, course).getValue();
        lessonRepository.save(lesson);

        segment = Segment.create(educator, lesson).getValue();
        lessonSegmentRepository.save(segment);
    }

    @Test
    @Transactional
    public void testCreate() {
        segment = Segment.create(educator, lesson).getValue();
        assertNotNull(segment.getId());
        lessonSegmentRepository.save(segment);

        assertEquals(lessonSegmentRepository.findById(segment.getId()).orElseThrow(), segment);
    }

    @Test
    public void testLessonSegmentNameAndDescription() {
        segment.setName("TestName");
        segment.setExerciseDescription(PromptFragment.of("TestExerciseDescription"));
        segment.setOverviewDescription(PromptFragment.of("TestOverviewDescription"));

        lessonSegmentRepository.save(segment);

        var fetched = lessonSegmentRepository.findById(segment.getId()).orElseThrow();
        assertEquals(fetched.getName(), "TestName");
        assertEquals("TestName", segment.getName());
        assertEquals("TestExerciseDescription", segment.getExerciseDescription().text());
        assertEquals("TestOverviewDescription", segment.getOverviewDescription().text());
    }

    @Test
    @Transactional
    public void testAddNextElement() {
        Segment segment1 = Segment.create(educator, lesson).getValue();
        Segment segment2 = Segment.create(educator, lesson).getValue();
        lessonSegmentRepository.save(segment1);
        lessonSegmentRepository.save(segment2);

        segment.addNextElement(segment1);
        lessonSegmentRepository.save(segment);

        var fetch1 = lessonSegmentRepository.findById(segment.getId()).orElseThrow();
        assertEquals(fetch1.getNextElements().stream().findFirst().orElseThrow(), segment1);

        segment.addNextElement(segment2);
        lessonSegmentRepository.save(segment);

        var fetch2 = lessonSegmentRepository.findById(segment.getId()).orElseThrow();
        assertEquals(fetch2.getNextElements().stream().skip(1).findFirst().orElseThrow(), segment2);
    }

    @Test
    @Transactional
    public void testSetPreviousElement() {
        Segment segment1 = Segment.create(educator, lesson).getValue();
        Segment segment2 = Segment.create(educator, lesson).getValue();
        lessonSegmentRepository.save(segment1);
        lessonSegmentRepository.save(segment2);

        segment.addNextElement(segment1);
        lessonSegmentRepository.save(segment);

        var fetch1 = lessonSegmentRepository.findById(segment.getId()).orElseThrow();
        assertEquals(fetch1.getNextElements().stream().findFirst().orElseThrow(), segment1);

        segment.addNextElement(segment2);
        lessonSegmentRepository.save(segment);

        var fetch2 = lessonSegmentRepository.findById(segment.getId()).orElseThrow();
        assertEquals(fetch2.getNextElements().stream().findFirst().orElseThrow(), segment2);

    }

}
