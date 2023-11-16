package com.edutie.edutiebackend.domain.learningresult;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.learningresult.identities.LearningResultId;

import com.edutie.edutiebackend.domain.student.identities.StudentId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends EntityBase<LearningResultId> {
    private LearningResourceId learningResourceId;
    private StudentId studentId;
    private HashMap<SkillId, Integer> skillPoints;

    /**
     * Assigns skill points to given skill
     * @param skillId id of skill
     * @param points points to assign
     */
    public void assignSkillPoints(SkillId skillId, int points)
    {
        skillPoints.put(skillId, points);
    }

    /**
     * removes skill from skill points mapping
     * @param skillId skill  to remove
     */
    public void removeSkillPoints(SkillId skillId)
    {
        skillPoints.remove(skillId);
    }

    /**
     * Retrieves points associated with given skill
     * @param skillId id of skill
     * @return amount of points as integer
     */
    public Integer getSkillPoints(SkillId skillId)
    {
        return skillPoints.get(skillId);
    }
}
