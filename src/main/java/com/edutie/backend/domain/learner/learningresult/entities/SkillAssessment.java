package com.edutie.backend.domain.learner.learningresult.entities;

import com.edutie.backend.domain.learner.learningresult.entities.base.Assessment;
import com.edutie.backend.domain.psychology.skill.Skill;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class SkillAssessment extends Assessment<Skill> {
}
