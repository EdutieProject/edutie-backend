package com.edutie.edutiebackend.domain.core.common.studenttraits;

import lombok.Getter;

/**
 * Intelligence enum describing intelligence types according to 8 intelligence theory
 */
@Getter
public enum Intelligence
{
    Visual("Visual"),
    Linguistic("Linguistic"),
    Interpersonal("Interpersonal"),
    Intrapersonal("Intrapersonal"),
    Logical("Logical"),
    Musical("Musical"),
    Kinesthetic("Kinesthetic"),
    Naturalistic("Naturalistic");


    private final String code;

    Intelligence(String code)
    {
        this.code = code;
    }

}
