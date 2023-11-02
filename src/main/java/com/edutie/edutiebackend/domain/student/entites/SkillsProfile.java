package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.studentTraits.Skill;
import com.edutie.edutiebackend.domain.common.base.EntityBase;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class SkillsProfile extends EntityBase<SkillsProfile> {
    private HashMap<Skill, Double> skillPoints;

    /**
     * Default constructor. Should be changed in the future to utilize database values.
     */
    public SkillsProfile() {
        skillPoints = new HashMap<>();
        for (Skill skill: Skill.values()) {
            skillPoints.put(skill, 0.0);
        }
    }


}
