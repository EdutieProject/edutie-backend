package com.edutie.edutiebackend.domain.core.student.validation;


import com.edutie.edutiebackend.domain.core.student.exceptions.InvalidBirthDateException;

import java.time.LocalDate;

/**
 * Class responsible for validating student's age based on their
 * date of birth. Contains rules for birthdate validation.
 */
public class StudentBirthdateValidator {
    public static boolean isValid(LocalDate studentBirthdate) throws InvalidBirthDateException {
        var studentAge = LocalDate.now().getYear() - studentBirthdate.getYear();
        if(
             studentAge > 3 &&
             studentAge < 99
        )
            return true;
        else
            throw new InvalidBirthDateException();
    }
}
