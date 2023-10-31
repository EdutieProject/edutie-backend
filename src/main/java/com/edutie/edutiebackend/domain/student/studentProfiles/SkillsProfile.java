package com.edutie.edutiebackend.domain.student.studentProfiles;

import com.edutie.edutiebackend.domain.student.interfaces.IStudentProfile;
import com.edutie.edutiebackend.domain.student.studentTraits.Skill;
import com.edutie.edutiebackend.domain.common.EntityBase;
import jakarta.persistence.Entity;

import java.util.HashMap;

@Entity
public class SkillsProfile extends EntityBase<SkillsProfile> implements IStudentProfile {
    private final HashMap<Skill, Double> skillPoints = new HashMap<>();

    /**
     * Default constructor. Should be changed in the future to utilize database values.
     */
    public SkillsProfile() {
        for (Skill skill: Skill.values()) {
            skillPoints.put(skill, 0.0);
        }
    }

    @Override
    public void adjust(byte learningResult) {
        // perform adjusting
    }
}
