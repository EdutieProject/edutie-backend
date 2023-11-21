package com.edutie.edutiebackend.domain.core.skill.validation;

import com.edutie.edutiebackend.domain.core.skill.exceptions.InvalidTraitMultiplierValueException;

/**
 * Class responsible for validating the value of Trait Multiplier.
 * Contains rules which define proper value of the multiplier.
 */
public class TraitMultiplierValueValidator {
    public static boolean isValid(double multiplierValue) throws InvalidTraitMultiplierValueException {
        if(
                multiplierValue > 0.0 && multiplierValue < 10.0
        )
            return true;
        else
            throw new InvalidTraitMultiplierValueException();
    }
}
