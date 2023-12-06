package com.edutie.edutiebackend.domain.core.lessonsegment.enums;

//TODO: Add source origins

public enum SourceOrigin {
    YOUTUBE("Youtube"),
    KHAN_ACADEMY("Khan Academy"),
    WIKIPEDIA("Wikipedia"),
    MATEMAKS("Matemaks"),
    OTHER("Other");

    private final String code;
    SourceOrigin(String code)
    {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
