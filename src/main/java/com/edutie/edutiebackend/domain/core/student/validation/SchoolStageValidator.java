package com.edutie.edutiebackend.domain.core.student.validation;

import com.edutie.edutiebackend.domain.core.student.exceptions.InvalidSchoolStageException;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;

import org.springframework.data.domain.Range;

import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;

/**
 * Class responsible for validating school stage. Contains
 * rules defining whether school stage is valid or not.
 */
public class SchoolStageValidator {

    private SchoolStage schoolStage;

    public SchoolStageValidator(SchoolStage schoolStage)
    {
        this.schoolStage=schoolStage;
    }
    private static boolean isValidCondition() {
        return (HighSchoolValidation(this.schoolStage) || TechnicalHighSchoolValidation(this.schoolStage)  || TertiarySchoolValidation(this.schoolStage));
    }
    private static boolean isValidRange(int left,int right,var gradeNo) {
        Range<int> range=new Range().leftOpen(left, right);
        var validRange=range.contains(gradeNo);
        return validRange;
    }
    public static boolean isValid() throws InvalidSchoolStageException {
        if(this.isValidCondition())
            return true;
        else
            throw new InvalidSchoolStageException();
    }

    private static boolean HighSchoolValidation()
    {
        var gradeNo = (this.schoolStage).gradeNumber();
        return (((this.schoolStage).schoolType() == SchoolType.HighSchool) && (this.isValidRange(0,4,gradeNo)));
    }

    private static boolean TechnicalHighSchoolValidation()
    {
        var gradeNo = (this.schoolStage).gradeNumber();
        return (((this.schoolStage).schoolType() == SchoolType.TechnicalHighSchool) && (this.isValidRange(0,3,gradeNo)));
    }

    private static boolean TertiarySchoolValidation()
    {
        var gradeNo = (this.schoolStage).gradeNumber();
        return (((this.schoolStage).schoolType() == SchoolType.TertiarySchool) && (this.isValidRange(0,5,gradeNo)));
    }
}
