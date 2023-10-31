package com.edutie.edutiebackend.domain.core.learningResource.learningResourceComponents.knowledgeSource;

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
