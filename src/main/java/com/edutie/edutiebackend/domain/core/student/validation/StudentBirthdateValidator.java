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
        if(studentAge > 3 && studentAge < 99)
            return true;
        else
            throw new InvalidBirthDateException();
    }
}
//A co w sytuacji, gdy chciałbyś wstawiać na platformę kursy od 18 lat.Typu pędzenie bimbru.
//A może by to spiąć z tym powiązaniem szkół, bo nagle ci wyjdzie, że 3 latek chodzi do szkoły wyższej. Teoretycznie to możliwe, ale jest to poniżej 0.000000001 promila szans.