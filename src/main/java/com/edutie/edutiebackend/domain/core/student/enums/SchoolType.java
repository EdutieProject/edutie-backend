package com.edutie.edutiebackend.domain.core.student.enums;

import lombok.Getter;

@Getter
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
}
