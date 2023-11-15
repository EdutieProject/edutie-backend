package com.edutie.edutiebackend.domain.skill;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.common.studenttraits.Intelligence;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * Skill is a high-level indicator of Learning Resource's knowledge requirements.
 * It contains necessary mappings for fundamental skill and intelligence utilized in
 * the task.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Skill extends EntityBase<SkillId> {
    private String name;
    HashMap<Ability, Double> abilityMultipliers = new HashMap<>();
    HashMap<Intelligence, Double> intelligenceMultipliers = new HashMap<>();
}
