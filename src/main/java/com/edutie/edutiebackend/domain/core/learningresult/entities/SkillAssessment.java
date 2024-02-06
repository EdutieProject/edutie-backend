package com.edutie.edutiebackend.domain.core.learningresult.entities;

import com.edutie.edutiebackend.domain.core.learningresult.entities.base.Assessment;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class SkillAssessment extends Assessment<Skill> {
}
