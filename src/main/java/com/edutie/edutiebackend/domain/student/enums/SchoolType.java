package com.edutie.edutiebackend.domain.student.enums;

public enum SchoolType
{
    HighSchool("High School"),
    TechnicalHighSchool("Technical High School"),
    TertiarySchool("Tertiary School");


    private final String code;

    SchoolType(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
}
