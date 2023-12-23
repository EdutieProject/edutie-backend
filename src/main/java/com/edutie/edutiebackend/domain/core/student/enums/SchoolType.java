package com.edutie.edutiebackend.domain.core.student.enums;

import lombok.Getter;

/**
 * Enum describing student's school type.
 */
@Getter
public enum SchoolType
{
    HighSchool("High School"),
    TechnicalHighSchool("Technical High School"),
    TertiarySchool("Tertiary School"),
    Homeschooling("Homeschooling"),
    NoSchool("No school");


    private final String code;

    SchoolType(String code)
    {
        this.code = code;
    }
}
