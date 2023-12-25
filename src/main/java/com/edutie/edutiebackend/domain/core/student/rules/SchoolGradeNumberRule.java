package com.edutie.edutiebackend.domain.core.student.rules;

import com.edutie.edutiebackend.domain.core.common.rule.RuleError;
import com.edutie.edutiebackend.domain.core.common.rule.Rule;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class SchoolGradeNumberRule implements Rule<SchoolStage> {

    Function<SchoolStage, Boolean> gradeNumberCondition = schoolStage -> switch (schoolStage.schoolType()){
        case HighSchool -> schoolStage.gradeNumber() > 0 && schoolStage.gradeNumber() <= 4;
        case TechnicalHighSchool -> schoolStage.gradeNumber() > 0 && schoolStage.gradeNumber() <= 5;
        case TertiarySchool -> schoolStage.gradeNumber() > 0 && schoolStage.gradeNumber() <=3 ;
        case Homeschooling, NoSchool -> true;
    };


    @Override
    public List<RuleError> check(SchoolStage schoolStage) {
        boolean gradeNumberCorrect = gradeNumberCondition.apply(schoolStage);
        if(gradeNumberCorrect)
            return Collections.emptyList();
        else
            return Collections.singletonList(
                new RuleError(this, "Invalid grade number for given school type")
            );
    }
}
