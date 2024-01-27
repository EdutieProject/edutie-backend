package com.edutie.edutiebackend.domain.core.student.rules;

import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.rule.Error;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class StudentAgeBoundsRule implements Rule<LocalDate> {

    Function<Integer, Boolean> studentAgeBoundsCondition = (age) -> age > 0 && age < 99;


    @Override
    public List<Error> check(LocalDate studentBirthdate) {
        var studentAge = LocalDate.now().getYear() - studentBirthdate.getYear();
        return studentAgeBoundsCondition.apply(studentAge) ?
                Collections.emptyList() :
                Collections.singletonList(
                        new Error(this, "Student's birthdate implies illegal age")
                );
    }
}
