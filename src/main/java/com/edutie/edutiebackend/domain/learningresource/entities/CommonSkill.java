package com.edutie.edutiebackend.domain.learningresource.entities;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.CommonSkillId;
import com.edutie.edutiebackend.domain.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.common.studenttraits.Skill;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EnumMap;

/**
 * Common skill is a high-level indicator of Learning Resource's knowledge requirements.
 * It contains necessary mappings for fundamental skill and intelligence utilized in
 * the task.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CommonSkill extends EntityBase<CommonSkillId> {
    private String name;
    EnumMap<Skill, Double> skillMultipliers = new EnumMap<>(Skill.class);
    EnumMap<Intelligence, Double> intelligenceMultipliers = new EnumMap<>(Intelligence.class);
}
