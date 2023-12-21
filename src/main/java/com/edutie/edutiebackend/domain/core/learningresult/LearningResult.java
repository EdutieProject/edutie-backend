package com.edutie.edutiebackend.domain.core.learningresult;

import java.util.HashMap;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.learningresult.exceptions.InvalidSkillPointsValueException;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.edutiebackend.domain.core.learningresult.validation.SkillPointsValidator;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.LearningReport;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends EntityBase<LearningResultId> {
    private LearningResourceId learningResourceId;
    private LearningReport learningReport;
    private StudentId studentId;
    private HashMap<SkillId, Integer> skillPoints;

    /**
     * Assigns skill points to given skill
     * @param skillId id of skill
     * @param points points to assign
     * @throws InvalidSkillPointsValueException exception thrown when points value is invalid
     * @see SkillPointsValidator Validating skill points value
     */
    public void assignSkillPoints(SkillId skillId, int points) throws InvalidSkillPointsValueException {
        if(SkillPointsValidator.isValid(points))
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
