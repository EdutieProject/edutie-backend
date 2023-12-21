package com.edutie.edutiebackend.domain.core.skill.validation;

import com.edutie.edutiebackend.domain.core.skill.exceptions.InvalidTraitMultiplierValueException;

/**
 * Class responsible for validating the value of Trait Multiplier.
 * Contains rules which define proper value of the multiplier.
 */
public class TraitMultiplierValueValidator {
    public static boolean isValid(double multiplierValue) throws InvalidTraitMultiplierValueException {
        if(multiplierValue > 0.0 && multiplierValue < 10.0) //WArtość 10 nie powinna być hardcodowana
        {
            return true;
        }
        else
        {
            throw new InvalidTraitMultiplierValueException();
        }
    }
}
//tutaj zastosować wzorzec projektowy strategii albo chociaż wywalić Excepti0on jako zwracany przez metodę