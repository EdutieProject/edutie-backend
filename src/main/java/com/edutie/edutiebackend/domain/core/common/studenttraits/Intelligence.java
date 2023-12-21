package com.edutie.edutiebackend.domain.core.common.studenttraits;

import lombok.Getter;

/**
 * Intelligence enum describing intelligence types according to 8 intelligence theory
 */
@Getter
public enum Intelligence
{
    VISUAL("Visual"),
    LINGUISTIC("Linguistic"),
    INTERPERSONAL("Interpersonal"),
    INTRAPERSONAL("Intrapersonal"),
    LOGICAL("Logical"),
    MUSICAL("Musical"),
    KINESTHETIC("Kinesthetic"),
    NATURALISTIC("Naturalistic");

    private final String code;

    Intelligence(String code)
    {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
