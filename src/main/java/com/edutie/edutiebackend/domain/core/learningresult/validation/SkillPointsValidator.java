package com.edutie.edutiebackend.domain.core.learningresult.validation;

import com.edutie.edutiebackend.domain.core.learningresult.exceptions.InvalidSkillPointsValueException;

/**
 * Class responsible for validating Skill Points amount.
 * It contains rules which define proper values for skill
 * points.
 */
public class SkillPointsValidator {
    public static boolean isValid(double skillPointsAmount) throws InvalidSkillPointsValueException {
        if (skillPointsAmount > 0 && skillPointsAmount < 100) // The bonds of Skill Points available for single skill in Learning Result
        {
            return true;
        }
        else
        {
            throw new InvalidSkillPointsValueException(); 
        }
    }
}
// Po co tutaj zwracać wyjątek i go potem obsługiwać. Skoro skill point nigdy nie bedzie poza tym zakresem zdefiniowanym w IF?