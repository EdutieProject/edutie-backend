package com.edutie.edutiebackend.domain.core.student.enums;

import lombok.Getter;

/**
 * Enum describing student's school type.
 */
@Getter
public enum SchoolType
{
    HIGH_SCHOOL("High School"),
    TECHNICAL_HIGH_SCHOOL("Technical High School"),
    TERTIARY_SCHOOL("Tertiary School"),
    HOMESCHOOLING("Homeschooling"),
    NO_SCHOOL("No School");


    private final String code;

    SchoolType(String code)
    {
        this.code = code;
    }
}
