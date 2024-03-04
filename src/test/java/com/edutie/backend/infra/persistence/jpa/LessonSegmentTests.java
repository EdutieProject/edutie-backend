package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.psychology.psychologist.Psychologist;
import com.edutie.backend.domain.psychology.psychologist.identities.PsychologistId;
import com.edutie.backend.domain.psychology.skill.Skill;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.creator.Creator;
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
import static org.springframework.util.ObjectUtils.isEmpty;

@SpringBootTest
@NoArgsConstructor
public class LessonSegmentTests {
    private final UserId testUserId = new UserId();
    private Creator creator;
    private Course course;
    private Lesson lesson;
    private LessonSegment lessonSegment;
    private Skill  skill;
    private Psychologist psychologist;


    @Autowired
    private CreatorRepository creatorRepository;
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
    @Autowired
    private PsychologistRepository psychologistRepository;

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

        lessonSegment = LessonSegment.create(creator, lesson);
        lessonSegmentRepository.save(lessonSegment);
    }

    @Test
    @Transactional
    public void testCreate() {
        lessonSegment = LessonSegment.create(creator, lesson);
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
    public void testAddSkill() {
        psychologist = Psychologist.create(testUserId);
        psychologistRepository.save(psychologist);

        skill = Skill.create(psychologist);
        skillRepository.save(skill);

        var testSkill = skillRepository.findById(skill.getId()).orElseThrow();
        assertEquals(testSkill, skill);

        lessonSegment.addSkill(skill);
        lessonSegmentRepository.save(lessonSegment);

        var fetched = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        assertEquals(fetched.getSkills().stream().findFirst().orElseThrow(), skill);
    }


    @Test
    @Transactional
    public void testRemoveSkill(){
        psychologist = Psychologist.create(testUserId);
        psychologistRepository.save(psychologist);

        skill = Skill.create(psychologist);
        skillRepository.save(skill);

        lessonSegment.addSkill(skill);
        //System.out.println(skill);
        lessonSegmentRepository.save(lessonSegment);


        lessonSegment.removeSkill(skill);
        lessonSegmentRepository.save(lessonSegment);

        var fetched = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        //System.out.println(fetched.getSkills());
        assertTrue(fetched.getSkills().isEmpty());
    }

    @Test
    @Transactional
    public void testAddNextElement() {
        LessonSegment lessonSegment1 = LessonSegment.create(creator, lesson);
        assertNotNull(lessonSegment1);
        assertNotNull(lessonSegment1.getId());
        lessonSegmentRepository.save(lessonSegment1);

        lessonSegment.addNextElement(lessonSegment1);
        lessonSegmentRepository.save(lessonSegment);

        var fetch = lessonSegmentRepository.findById(lessonSegment.getId()).orElseThrow();
        assertNotNull(fetch.getNextElements());
        System.out.println(fetch.getNextElements());
        assertEquals(fetch.getNextElements().stream().findFirst().orElseThrow(), lessonSegment1);
    }


}
