package com.edutie.domain.core.learning.learningexperience.valueobjects;

import com.edutie.domain.core.common.enums.PersistableEnum;
import com.edutie.infrastructure.persistence.config.AbstractEnumConverter;

public enum VisualisationType implements PersistableEnum<String> {
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
