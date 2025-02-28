package com.edutie.domain.core.common.paragraph;

import com.edutie.domain.core.common.enums.CodedEnum;
import com.edutie.infrastructure.persistence.config.AbstractEnumConverter;

public enum TextContentType implements CodedEnum<String> {
    SIMPLE_TEXT("SimpleText"),
    HEADING("Heading");

    private final String code;

    TextContentType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static class Converter extends AbstractEnumConverter<TextContentType, String> {
        public Converter() {
            super(TextContentType.class);
        }
    }
}
