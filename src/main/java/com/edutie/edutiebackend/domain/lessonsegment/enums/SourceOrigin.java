package com.edutie.edutiebackend.domain.lessonsegment.enums;

//TODO: Is it a constant enum or should it be an entity?

public enum SourceOrigin {
    YouTube("Youtube"),
    KhanAcademy("KhanAcademy"),
    Wikipedia("Wikipedia"),
    Other("Other");

    SourceOrigin(String code)
    { }
}
