package com.edutie.edutiebackend.infra.persistence.jpa;

import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.entities.LearningAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.entities.SkillAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.entities.base.Assessment;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.edutiebackend.domain.core.learningrequirement.LearningRequirement;
import com.edutie.edutiebackend.domain.core.learningrequirement.identities.LearningRequirementId;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.skill.entities.AbilityIndicator;
import com.edutie.edutiebackend.domain.core.skill.entities.IntelligenceIndicator;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.entites.AbilityLearningParameter;
import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;
import com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssessmentEntitiesTests {

    private final UserId testUser = new UserId();

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


    @Test
    public void skillCreateRetrieveTest() {
        Skill skill = new Skill();
        skill.setCreatedBy(testUser);
        skill.setId(new SkillId());
        skill.setName("Sample skill");

        skillRepository.save(skill);

        var fetchedSkill = skillRepository.findById(skill.getId());
        assertTrue(fetchedSkill.isPresent());
        assertEquals(fetchedSkill.get().getName(), skill.getName());
    }

    @Test
    public void skillWithIndicatorsCreateRetrieveTest() {
        Skill skill = new Skill();
        skill.setId(new SkillId());
        skill.setCreatedBy(testUser);
        skill.assignTraitIndicator(Ability.CREATIVITY, 2, AbilityIndicator.class);
        skill.assignTraitIndicator(Intelligence.LOGICAL, 1, IntelligenceIndicator.class);

        intelligenceIndicatorRepository.save(skill.getIntelligenceIndicators().stream().findFirst().get());
        abilityIndicatorRepository.save(skill.getAbilityIndicators().stream().findFirst().get());
        skillRepository.save(skill);

        var fetchedSkill = skillRepository.findById(skill.getId());

        assertTrue(fetchedSkill.isPresent());
        assertFalse(skill.getIndicators().isEmpty());
        assertEquals(skill.getIndicators().stream().filter(o->o.getTrait() instanceof Ability).findFirst().get().getValue(), 2);
    }

    @Test
    public void studentCreateRetrieveTest() {
        Student student = new Student();
        student.setId(new StudentId());
        student.setCreatedBy(testUser);
        studentRepository.save(student);

        var fetchedStudent = studentRepository.findById(student.getId()).orElse(new Student());
        assertEquals(testUser, fetchedStudent.getCreatedBy());
    }

    @Test
    public void studentCreateRetrieveWithParametersTest() {
        Student student = new Student();
        student.setId(new StudentId());
        student.setCreatedBy(testUser);
        student.adaptLearningParameters(AbilityLearningParameter.class, Ability.CRITICAL_THINKING, 10.0);

        abilityLearningParamRepository.save(
                student.getAbilityLearningParameters().stream().findFirst().get()
        );

        studentRepository.save(student);

        var fetchedStudent = studentRepository.save(student);
        assertFalse(student.getAllLearningParameters().isEmpty());
    }

    @Test
    public void studentSchoolStageTest() {
        Student student = new Student();
        student.setId(new StudentId());
        student.setCreatedBy(testUser);
        student.setSchoolStage(SchoolType.HIGH_SCHOOL, 2, "Mat-Fiz");
        studentRepository.save(student);

        var fetchedStudent = studentRepository.findById(student.getId()).orElseThrow();
        assertEquals(fetchedStudent.getSchoolStage().gradeNumber(), 2);
    }

    @Test
    public void learningResultCreateRetrieveTest() {
        LearningResult learningResult = new LearningResult();
        learningResult.setId(new LearningResultId());
        learningResult.setCreatedBy(testUser);
        learningResultRepository.save(learningResult);

        var fetchedResult = learningResultRepository.findById(learningResult.getId());
        assertTrue(fetchedResult.isPresent());
        assertEquals(learningResult.getId(), fetchedResult.get().getId());
    }

    @Test
    public void learningResultRelationshipsTest() {
        Skill skill = new Skill();
        skill.setId(new SkillId());
        skill.setCreatedBy(testUser);
        LearningRequirement learningRequirement = new LearningRequirement();
        learningRequirement.setId(new LearningRequirementId());
        learningRequirement.setCreatedBy(testUser);
        skillRepository.save(skill);
        learningRequirementRepository.save(learningRequirement);

        LearningResult learningResult = new LearningResult();
        learningResult.setId(new LearningResultId());
        learningResult.setCreatedBy(testUser);
        var skillAssessment = Assessment.create(skill, 30);
        skillAssessmentRepository.save(skillAssessment);
        var learningAssessment = Assessment.create(learningRequirement, 20);
        learningAssessmentRepository.save(learningAssessment);
        learningResult.addAssessment(skillAssessment, SkillAssessment.class);
        learningResult.addAssessment(learningAssessment, LearningAssessment.class);
        learningResultRepository.save(learningResult);

        var fetchedLr = learningResultRepository.findById(learningResult.getId());
        assertEquals(20 , learningResult.getLearningAssessments().stream().findFirst().get().getAssessmentPoints());
        assertEquals(30 , learningResult.getSkillAssessments().stream().findFirst().get().getAssessmentPoints());
        assertEquals(2 , learningResult.getAllAssessments().size());
    }
}
