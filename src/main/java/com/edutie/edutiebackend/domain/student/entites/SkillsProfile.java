package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.identities.SkillsProfileId;
import com.edutie.edutiebackend.domain.common.studenttraits.Skill;
import com.edutie.edutiebackend.domain.student.entites.base.StudentTraitProfile;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EnumMap;

/**
 * Student's profile made for abilities and skill set tracking.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class SkillsProfile extends StudentTraitProfile<Skill, SkillsProfileId> {
    /**
     * Base constructor of skills profile, initializes mapping.
     */
    public SkillsProfile()
    {
        parameters = new EnumMap<>(Skill.class);
    }
}
