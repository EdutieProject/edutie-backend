package com.edutie.edutiebackend.domain.core.student.enums;

import lombok.Getter;

@Getter
public enum SchoolType //Jeśli zakładasz typy szkoły to lepiej założyć PrimarySchool,SecondarySchool i HighSchool oraz NoSchool(bo zawsze można założyć edukację domową, lub kogoś, kto będzie sięchciał uczyć ale już skończył edukację szkolną)
{
    PrimarySchool("Primary School")
    SecondarySchool("Secondary School")
    HighSchool("High School")
    NoSchool("No School")

    private final String schoolTypeCode;

    SchoolType(String code)
    {
        this.schoolTypeCode = code;
    }
    public String getSchoolType()
    {
        return schoolTypeCode;
    }
}
