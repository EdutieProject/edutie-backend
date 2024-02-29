package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


//    @Test
//    public void skillCreateRetrieveTest() {
//        Skill skill = new Skill();
//        skill.setCreatedBy(testUser);
//        skill.setId(new SkillId());
//        skill.setName("Sample skill");
//
//        skillRepository.save(skill);
//
//        var fetchedSkill = skillRepository.findById(skill.getId());
//        assertTrue(fetchedSkill.isPresent());
//        assertEquals(fetchedSkill.get().getName(), skill.getName());
//    }
//
//    @Test
//    public void skillWithIndicatorsCreateRetrieveTest() {
//        Skill skill = new Skill();
//        skill.setId(new SkillId());
//        skill.setCreatedBy(testUser);
//        skill.assignTraitIndicator(Ability.CREATIVITY, 2, AbilityIndicator.class);
//        skill.assignTraitIndicator(Intelligence.LOGICAL, 1, IntelligenceIndicator.class);
//
//        intelligenceIndicatorRepository.save(skill.getIntelligenceIndicators().stream().findFirst().get());
//        abilityIndicatorRepository.save(skill.getAbilityIndicators().stream().findFirst().get());
//        skillRepository.save(skill);
//
//        var fetchedSkill = skillRepository.findById(skill.getId());
//
//        assertTrue(fetchedSkill.isPresent());
//        assertFalse(skill.getIndicators().isEmpty());
//        Assertions.assertEquals(skill.getIndicators().stream().filter(o->o.getTrait() instanceof Ability).findFirst().get().getValue(), 2);
//    }
//
//    @Test
//    public void studentCreateRetrieveTest() {
//        Student student = new Student();
//        student.setId(new StudentId());
//        student.setCreatedBy(testUser);
//        studentRepository.save(student);
//
//        var fetchedStudent = studentRepository.findById(student.getId()).orElse(new Student());
//        Assertions.assertEquals(testUser, fetchedStudent.getCreatedBy());
//    }
//
//    @Test
//    public void studentCreateRetrieveWithParametersTest() {
//        Student student = new Student();
//        student.setId(new StudentId());
//        student.setCreatedBy(testUser);
//        student.adaptLearningParameters(AbilityLearningParameter.class, Ability.CRITICAL_THINKING, 10.0);
//
//        abilityLearningParamRepository.save(
//                student.getAbilityLearningParameters().stream().findFirst().get()
//        );
//
//        studentRepository.save(student);
//
//        var fetchedStudent = studentRepository.save(student);
//        assertFalse(student.getAllLearningParameters().isEmpty());
//    }
//
//    @Test
//    public void studentSchoolStageTest() {
//        Student student = new Student();
//        student.setId(new StudentId());
//        student.setCreatedBy(testUser);
//        student.setSchoolStage(SchoolType.HIGH_SCHOOL, 2, "Mat-Fiz");
//        studentRepository.save(student);
//
//        var fetchedStudent = studentRepository.findById(student.getId()).orElseThrow();
//        Assertions.assertEquals(fetchedStudent.getSchoolStage().gradeNumber(), 2);
//    }
//
//    @Test
//    public void learningResultCreateRetrieveTest() {
//        LearningResult learningResult = new LearningResult();
//        learningResult.setId(new LearningResultId());
//        learningResult.setCreatedBy(testUser);
//        learningResultRepository.save(learningResult);
//
//        var fetchedResult = learningResultRepository.findById(learningResult.getId());
//        assertTrue(fetchedResult.isPresent());
//        assertEquals(learningResult.getId(), fetchedResult.get().getId());
//    }
//
//    @Test
//    public void learningResultRelationshipsTest() {
//        Skill skill = new Skill();
//        skill.setId(new SkillId());
//        skill.setCreatedBy(testUser);
//        LearningRequirement learningRequirement = new LearningRequirement();
//        learningRequirement.setId(new LearningRequirementId());
//        learningRequirement.setCreatedBy(testUser);
//        skillRepository.save(skill);
//        learningRequirementRepository.save(learningRequirement);
//
//        LearningResult learningResult = new LearningResult();
//        learningResult.setId(new LearningResultId());
//        learningResult.setCreatedBy(testUser);
//        var skillAssessment = Assessment.create(skill, 30);
//        skillAssessmentRepository.save(skillAssessment);
//        var learningAssessment = Assessment.create(learningRequirement, 20);
//        learningAssessmentRepository.save(learningAssessment);
//        learningResult.addAssessment(skillAssessment, SkillAssessment.class);
//        learningResult.addAssessment(learningAssessment, LearningAssessment.class);
//        learningResultRepository.save(learningResult);
//
//        var fetchedLr = learningResultRepository.findById(learningResult.getId());
//        Assertions.assertEquals(20 , learningResult.getLearningAssessments().stream().findFirst().get().getAssessmentPoints());
//        Assertions.assertEquals(30 , learningResult.getSkillAssessments().stream().findFirst().get().getAssessmentPoints());
//        assertEquals(2 , learningResult.getAllAssessments().size());
//    }
}
