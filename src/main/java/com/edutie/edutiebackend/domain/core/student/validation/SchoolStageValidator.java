package com.edutie.edutiebackend.domain.core.student.validation;

import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.core.student.exceptions.InvalidSchoolStageException;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;

/**
 * Class responsible for validating school stage. Contains
 * rules defining whether school stage is valid or not.
 */
public class SchoolStageValidator {
    private static boolean primarySchoolValidation(SchoolStage schoolStage)
    {
        var gradeNo = schoolStage.gradeNumber();
        return (schoolStage.schoolType() == SchoolType.PrimarySchool
                && gradeNo > 0
                && gradeNo <= 4
        );
    }

    private static boolean secondarySchoolValidation(SchoolStage schoolStage)
    {
        var gradeNo = schoolStage.gradeNumber();
        return (schoolStage.schoolType() == SchoolType.SecondarySchool
                && gradeNo > 0
                && gradeNo <= 5
        );
    }

    private static boolean highSchoolValidation(SchoolStage schoolStage)
    {
        var gradeNo = schoolStage.gradeNumber();
        return (schoolStage.schoolType() == SchoolType.HighSchool
                && gradeNo > 0
                && gradeNo <= 3
        );
    }
    private static boolean noSchoolValidation(SchoolStage schoolStage)
    {

    }
        

    public static boolean isValid(SchoolStage schoolStage) throws InvalidSchoolStageException {
        if(primarySchoolValidation(schoolStage) || secondarySchoolValidation(schoolStage)  || highSchoolValidation(schoolStage) || noSchoolValidation(schoolStage))
            return true;
        else
            throw new InvalidSchoolStageException();
    }
    //Ta klasa jest przykładem tego, że nie umiesz wzorców projektowych. W każdym razie tu jest problem i trzeba zrobić refaktoryzacje. Bo wystarczy zbudowaćjedną metode private, która będzie zastępować te 3.
}
