package com.edutie.backend.domain.core;

import com.edutie.backend.domain.core.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.core.learningresult.LearningResult;
import com.edutie.backend.domain.core.learningresult.entities.LearningAssessment;
import com.edutie.backend.domain.core.learningresult.entities.SkillAssessment;
import com.edutie.backend.domain.core.learningresult.entities.base.Assessment;
import com.edutie.backend.domain.core.lessonsegment.LessonSegment;
import com.edutie.backend.domain.core.skill.Skill;
import com.edutie.backend.domain.core.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LearningResultTests {

    private final Student mockStudent = new Student();

    @Test
    public void learningResultInitializationTest() {
        LessonSegment lessonSegment = new LessonSegment();
        LearningResult learningResult = new LearningResult(lessonSegment, mockStudent);

        assertTrue(learningResult.getAllAssessments().isEmpty());
    }

    @Test
    public void learningResultAssessmentApiTest() {
        Skill skill = new Skill();
        LearningRequirement learningRequirement = new LearningRequirement();
        LearningResult learningResult = new LearningResult();

        var skillAssessment = Assessment.create(skill, 50);
        var learningAssessment = Assessment.create(learningRequirement, 20);
        learningResult.addAssessment(skillAssessment, SkillAssessment.class);
        learningResult.addAssessment(learningAssessment, LearningAssessment.class);

        assertEquals(2,learningResult.getAllAssessments().size());
    }
}
