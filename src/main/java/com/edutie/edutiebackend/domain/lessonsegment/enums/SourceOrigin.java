package com.edutie.edutiebackend.domain.lessonsegment.enums;

//TODO: Is it a constant enum or should it be an entity?
public enum SourceOrigin {
    YouTube("Youtube"),
    KhanAcademy("KhanAcademy"),
    Wikipedia("Wikipedia"),
    Other("Other");

    private final String code;

    SourceOrigin(String code)
    {
        this.code = code;
    }
}
