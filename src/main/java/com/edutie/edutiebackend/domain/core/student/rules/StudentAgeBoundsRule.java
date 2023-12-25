package com.edutie.edutiebackend.domain.core.student.rules;

import com.edutie.edutiebackend.domain.core.common.rule.Rule;
import com.edutie.edutiebackend.domain.core.common.rule.RuleError;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class StudentAgeBoundsRule implements Rule<LocalDate> {

    Function<Integer, Boolean> studentAgeBoundsCondition = (age) -> age > 0 && age < 99;


    @Override
    public List<RuleError> check(LocalDate studentBirthdate) {
        var studentAge = LocalDate.now().getYear() - studentBirthdate.getYear();
        return studentAgeBoundsCondition.apply(studentAge) ?
                Collections.emptyList() :
                Collections.singletonList(
                        new RuleError(this, "Student's birthdate implies illegal age")
                );
    }
}
