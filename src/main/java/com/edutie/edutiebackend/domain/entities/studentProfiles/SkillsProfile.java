package com.edutie.edutiebackend.domain.entities.studentProfiles;

import com.edutie.edutiebackend.domain.entities.studentProfiles.interfaces.IStudentProfile;
import com.edutie.edutiebackend.domain.enums.Skill;

import java.util.HashMap;

public class SkillsProfile implements IStudentProfile {
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
