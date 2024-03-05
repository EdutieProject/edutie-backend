package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.common.studenttraits.Ability;
import com.edutie.backend.domain.common.studenttraits.Intelligence;
import com.edutie.backend.domain.learner.learningresult.LearningResult;
import com.edutie.backend.domain.learner.learningresult.entities.LearningAssessment;
import com.edutie.backend.domain.learner.learningresult.entities.SkillAssessment;
import com.edutie.backend.domain.learner.learningresult.identities.AssessmentId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.entites.AbilityLearningParameter;
import com.edutie.backend.domain.learner.student.enums.SchoolType;
import com.edutie.backend.domain.psychology.psychologist.Psychologist;
import com.edutie.backend.domain.psychology.skill.Skill;
import com.edutie.backend.domain.psychology.skill.entities.AbilityIndicator;
import com.edutie.backend.domain.psychology.skill.entities.IntelligenceIndicator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.domain.studyprogram.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssessmentEntitiesTests {

    private final UserId testUser = new UserId();
    private Skill skill;
    private Psychologist psychologist;
    private Science science;
    private Creator creator;
    private Student student;
    private LearningResult learningResult;
    private Lesson lesson;
    private LessonSegment  lessonSegment;
    private Course course;
    private LearningRequirement learningRequirement;


    @Autowired
    SkillRepository skillRepository;
    @Autowired
    IntelligenceIndicatorRepository intelligenceIndicatorRepository;
    @Autowired
    AbilityIndicatorRepository abilityIndicatorRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AbilityLearningParamRepository abilityLearningParamRepository;
    @Autowired
    IntelligenceLearningParamRepository intelligenceLearningParamRepository;
    @Autowired
    LearningResultRepository learningResultRepository;
    @Autowired
    SkillAssessmentRepository skillAssessmentRepository;
    @Autowired
    LearningAssessmentRepository learningAssessmentRepository;
    @Autowired
    LearningRequirementRepository learningRequirementRepository;
    @Autowired
    CreatorRepository creatorRepository;
    @Autowired
    ScienceRepository scienceRepository;
    @Autowired
    PsychologistRepository psychologistRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    LessonSegmentRepository lessonSegmentRepository;



    @BeforeEach
    public void testSetUp() {
        creator = Creator.create(testUser);
        creatorRepository.save(creator);

        science = Science.create(testUser);
        scienceRepository.save(science);

        course = Course.create(creator, science);
        courseRepository.save(course);

        psychologist = Psychologist.create(testUser);
        psychologistRepository.save(psychologist);

        skill = Skill.create(psychologist);
        skillRepository.save(skill);

        student = Student.create(testUser);
        studentRepository.save(student);

        lesson = Lesson.create(creator, course);
        lessonRepository.save(lesson);

        lessonSegment = LessonSegment.create(creator, lesson);
        lessonSegmentRepository.save(lessonSegment);

        learningResult = LearningResult.create(student, lessonSegment);
        learningResultRepository.save(learningResult);
    }

    @Test
    public void skillCreateRetrieveTest() {
        skill.setName("Sample_skill");
        skillRepository.save(skill);

        var fetchedSkill = skillRepository.findById(skill.getId()).orElseThrow();
        assertEquals(fetchedSkill.getName(), skill.getName());
    }

    @Test
    public void skillWithIndicatorsCreateRetrieveTest() {
        skill.assignTraitIndicator(Ability.CREATIVITY, 2, AbilityIndicator.class);
        skill.assignTraitIndicator(Intelligence.LOGICAL, 1, IntelligenceIndicator.class);

        intelligenceIndicatorRepository.save(skill.getIntelligenceIndicators().stream().findFirst().get());
        abilityIndicatorRepository.save(skill.getAbilityIndicators().stream().findFirst().get());
        skillRepository.save(skill);

        var fetchedSkill = skillRepository.findById(skill.getId());

        assertTrue(fetchedSkill.isPresent());
        assertFalse(skill.getIndicators().isEmpty());
        Assertions.assertEquals(skill.getIndicators().stream().filter(o->o.getTrait() instanceof Ability).findFirst().get().getValue(), 2);
    }
//
    @Test
    public void studentCreateRetrieveTest() {
        student = Student.create(testUser);
        studentRepository.save(student);

        var fetchedStudent = studentRepository.findById(student.getId()).orElse(new Student());
        Assertions.assertEquals(testUser, fetchedStudent.getCreatedBy());
    }


    //TODO WHAT IS HAPPENING IN THIS TEST????

    @Test
    public void studentCreateRetrieveWithParametersTest() {
        student.adaptLearningParameters(AbilityLearningParameter.class, Ability.CRITICAL_THINKING, 10.0);

        abilityLearningParamRepository.save(
                student.getAbilityLearningParameters().stream().findFirst().get());

        studentRepository.save(student);

        //var fetchedStudent = studentRepository.save(student); ??????????
        var fetchedStudent  = studentRepository.findById(student.getId()).orElseThrow();

        assertEquals(fetchedStudent.getAllLearningParameters(), student.getAllLearningParameters());
    }

    @Test
    public void studentSchoolStageTest() {
        student.setSchoolStage(SchoolType.HIGH_SCHOOL, 2, "Mat-Fiz");
        studentRepository.save(student);

        var fetchedStudent = studentRepository.findById(student.getId()).orElseThrow();
        Assertions.assertEquals(fetchedStudent.getSchoolStage().gradeNumber(), 2);
    }

    @Test
    public void learningResultCreateRetrieveTest() {
        var fetchedResult = learningResultRepository.findById(learningResult.getId());
        assertTrue(fetchedResult.isPresent());
        assertEquals(learningResult.getId(), fetchedResult.get().getId());
    }

    @Test
    public void learningResultRelationshipsTest() {

        learningRequirement = LearningRequirement.create(creator,science);
        learningRequirementRepository.save(learningRequirement);

//        var skillAssessment = Assessment.crea(skill, 30);

        var skillAssessment = new SkillAssessment();
        var assessmentId = new AssessmentId();
        skillAssessment.setId(assessmentId);
        skillAssessment.setEntity(skill);
        skillAssessment.setAssessmentPoints(30);
        skillAssessmentRepository.save(skillAssessment);


        //var learningAssessment = Assessment.create(learningRequirement, 20);

        var learningAssessment = new LearningAssessment();
        var learningAssesssmentId = new AssessmentId();
        learningAssessment.setId(learningAssesssmentId);
        learningAssessment.setEntity(learningRequirement);
        learningAssessment.setAssessmentPoints(20);
        learningAssessmentRepository.save(learningAssessment);

        learningResult.addAssessment(skillAssessment, SkillAssessment.class);
        learningResult.addAssessment(learningAssessment, LearningAssessment.class);
        learningResultRepository.save(learningResult);

        var fetchedLr = learningResultRepository.findById(learningResult.getId());
        Assertions.assertEquals(20 , learningResult.getLearningAssessments().stream().findFirst().get().getAssessmentPoints());
        Assertions.assertEquals(30 , learningResult.getSkillAssessments().stream().findFirst().get().getAssessmentPoints());
        assertEquals(2 , learningResult.getAllAssessments().size());
    }
}
