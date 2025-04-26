package com.edutie.domain.core.learning.common;

import com.edutie.domain.core.common.enums.CodedEnum;

public enum LearningObjectiveType implements CodedEnum<String> {
    REMEMBER("Remember"),
    UNDERSTAND("Understand"),
    APPLY("Apply"),
    ANALYZE("Analyze");

    private final String code;

    LearningObjectiveType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
