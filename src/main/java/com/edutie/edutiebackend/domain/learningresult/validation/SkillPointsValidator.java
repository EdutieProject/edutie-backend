package com.edutie.edutiebackend.domain.learningresult.validation;

import com.edutie.edutiebackend.domain.learningresult.exceptions.InvalidSkillPointsValueException;

/**
 * Class responsible for validating Skill Points amount.
 * It contains rules which define proper values for skill
 * points.
 */
public class SkillPointsValidator {
    public static boolean isValid(double skillPointsAmount) throws InvalidSkillPointsValueException {
        if (
                // The bonds of Skill Points available for single skill in Learning Result
                skillPointsAmount > 0 && skillPointsAmount < 100
        ) return true;
        else
            throw new InvalidSkillPointsValueException();
    }
}
