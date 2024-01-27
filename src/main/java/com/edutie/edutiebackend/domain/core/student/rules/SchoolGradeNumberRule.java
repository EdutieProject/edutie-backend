package com.edutie.edutiebackend.domain.core.student.rules;

import com.edutie.edutiebackend.domain.rule.Error;
import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class SchoolGradeNumberRule implements Rule<SchoolStage> {

    Function<SchoolStage, Boolean> gradeNumberCondition = schoolStage -> switch (schoolStage.schoolType()){
        case HIGH_SCHOOL -> schoolStage.gradeNumber() > 0 && schoolStage.gradeNumber() <= 4;
        case TECHNICAL_HIGH_SCHOOL -> schoolStage.gradeNumber() > 0 && schoolStage.gradeNumber() <= 5;
        case TERTIARY_SCHOOL -> schoolStage.gradeNumber() > 0 && schoolStage.gradeNumber() <=3 ;
        case HOMESCHOOLING, NO_SCHOOL -> true;
    };


    @Override
    public List<Error> check(SchoolStage schoolStage) {
        boolean gradeNumberCorrect = gradeNumberCondition.apply(schoolStage);
        if(gradeNumberCorrect)
            return Collections.emptyList();
        else
            return Collections.singletonList(
                new Error(this, "Invalid grade number for given school type")
            );
    }
}
