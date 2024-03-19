package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.skill.Skill;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.*;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@NoArgsConstructor
public class LessonSegmentTests {
    private final UserId testUserId = new UserId();
    private final AdminId adminId = new AdminId();
    private Lesson lesson;
    private LessonSegment lessonSegment;
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
    @Autowired
    private SkillRepository skillRepository;

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

        lessonSegment = LessonSegment.create(educator, lesson).getValue();
        lessonSegmentRepository.save(lessonSegment);
    }

    @Test
    @Transactional
    public void testCreate() {
        lessonSegment = LessonSegment.create(educator, lesson).getValue();
        assertNotNull(lessonSegment.getId());
        lessonSegmentRepository.save(lessonSegment);

        assertEquals(lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow(), lessonSegment);
    }

    @Test
    public void testLessonSegmentNameAndDescription() {
        lessonSegment.setName("TestName");
        lessonSegment.setExerciseDescription(PromptFragment.of("TestExerciseDescription"));
        lessonSegment.setOverviewDescription(PromptFragment.of("TestOverviewDescription"));

        lessonSegmentRepository.save(lessonSegment);

        var fetched = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        assertEquals(fetched.getName(), "TestName");
        assertEquals("TestName", lessonSegment.getName());
        assertEquals("TestExerciseDescription",lessonSegment.getExerciseDescription().text());
        assertEquals("TestOverviewDescription",lessonSegment.getOverviewDescription().text());
    }

    @Test
    @Transactional
    public void testAddNextElement() {
        LessonSegment lessonSegment1 = LessonSegment.create(educator, lesson).getValue();
        LessonSegment lessonSegment2 = LessonSegment.create(educator, lesson).getValue();
        lessonSegmentRepository.save(lessonSegment1);
        lessonSegmentRepository.save(lessonSegment2);

        lessonSegment.addNextElement(lessonSegment1);
        lessonSegmentRepository.save(lessonSegment);

        var fetch1 = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        assertEquals(fetch1.getNextElements().stream().findFirst().orElseThrow(), lessonSegment1);

        lessonSegment.addNextElement(lessonSegment2);
        lessonSegmentRepository.save(lessonSegment);

        var fetch2 = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        assertEquals(fetch2.getNextElements().stream().skip(1).findFirst().orElseThrow(), lessonSegment2);
    }

    @Test
    @Transactional
    public void testSetPreviousElement(){
        LessonSegment lessonSegment1 = LessonSegment.create(educator, lesson).getValue();
        LessonSegment lessonSegment2 = LessonSegment.create(educator, lesson).getValue();
        lessonSegmentRepository.save(lessonSegment1);
        lessonSegmentRepository.save(lessonSegment2);

        lessonSegment.addNextElement(lessonSegment1);
        lessonSegmentRepository.save(lessonSegment);

        var fetch1 = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        assertEquals(fetch1.getNextElements().stream().findFirst().orElseThrow(), lessonSegment1);

        lessonSegment.addNextElement(lessonSegment2);
        lessonSegmentRepository.save(lessonSegment);

        var fetch2 = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        assertEquals(fetch2.getNextElements().stream().findFirst().orElseThrow(), lessonSegment2);

    }

}
