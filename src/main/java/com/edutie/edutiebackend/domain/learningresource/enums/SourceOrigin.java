package com.edutie.edutiebackend.domain.learningresource.enums;

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
