package com.edutie.edutiebackend.domain.student.validation;

import com.edutie.edutiebackend.domain.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.student.exceptions.InvalidSchoolStageException;
import com.edutie.edutiebackend.domain.student.valueobjects.SchoolStage;

/**
 * Class responsible for validating school stage. Contains
 * rules defining whether school stage is valid or not.
 */
public class SchoolStageValidator {
    public static boolean isValid(SchoolStage schoolStage) throws InvalidSchoolStageException {
        if(
                HighSchoolValidation(schoolStage) || TechnicalHighSchoolValidation(schoolStage)  || TertiarySchoolValidation(schoolStage)
        )
            return true;
        else
            throw new InvalidSchoolStageException();
    }

    private static boolean HighSchoolValidation(SchoolStage schoolStage)
    {
        var gradeNo = schoolStage.gradeNumber();
        return (schoolStage.schoolType() == SchoolType.HighSchool
                && gradeNo > 0
                && gradeNo <= 4
        );
    }

    private static boolean TechnicalHighSchoolValidation(SchoolStage schoolStage)
    {
        var gradeNo = schoolStage.gradeNumber();
        return (schoolStage.schoolType() == SchoolType.TechnicalHighSchool
                && gradeNo > 0
                && gradeNo <= 5
        );
    }

    private static boolean TertiarySchoolValidation(SchoolStage schoolStage)
    {
        var gradeNo = schoolStage.gradeNumber();
        return (schoolStage.schoolType() == SchoolType.TertiarySchool
                && gradeNo > 0
                && gradeNo <= 3
        );
    }
}
