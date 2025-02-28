package com.edutie.domain.core.learning.learningexperience.valueobjects;

import com.edutie.domain.core.common.enums.CodedEnum;
import com.edutie.infrastructure.persistence.config.AbstractEnumConverter;

public enum VisualisationType implements CodedEnum<String> {
    MERMAID("Mermaid");

    private final String code;

    VisualisationType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static class Converter extends AbstractEnumConverter<VisualisationType, String> {
        public Converter() {
            super(VisualisationType.class);
        }
    }
}
