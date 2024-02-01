package com.edutie.edutiebackend.infra.persistence.jpa;

import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.skill.entities.AbilityIndicator;
import com.edutie.edutiebackend.domain.core.skill.entities.IntelligenceIndicator;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;
import com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssessmentEntitiesTests {

    private final UserId mockUser = new UserId();

    @Autowired
    SkillRepository skillRepository;
    @Autowired
    IntelligenceIndicatorRepository intelligenceIndicatorRepository;
    @Autowired
    AbilityIndicatorRepository abilityIndicatorRepository;
    @Autowired
    StudentRepository studentRepository;
//    @Autowired
//    AbilityLearningParamRepository abilityLearningParamRepository;
//    @Autowired
//    IntelligenceLearningParamRepository intelligenceLearningParamRepository;

    @Test
    public void skillCreateRetrieveTest() {
        Skill skill = new Skill();
        skill.setCreatedBy(mockUser);
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
        skill.setCreatedBy(mockUser);
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
        student.setCreatedBy(mockUser);
        studentRepository.save(student);

        var fetchedStudent = studentRepository.findById(student.getId()).orElse(new Student());
        assertEquals(mockUser, fetchedStudent.getCreatedBy());
    }
}
