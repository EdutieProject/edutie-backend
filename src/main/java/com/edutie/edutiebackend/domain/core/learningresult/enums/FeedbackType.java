package com.edutie.edutiebackend.domain.core.learningresult.enums;

import com.edutie.edutiebackend.domain.repository.converter.enums.AbstractEnumConverter;
import com.edutie.edutiebackend.domain.repository.converter.enums.PersistableEnum;
import lombok.Getter;

/**
 * Enum describing general opinion described by feedback.
 * Serves as a one-off alternative for user to know about
 * feedback opinion.
 */
//TODO: broaden feedback types
@Getter
public enum FeedbackType implements PersistableEnum<String> {
    POSITIVE("Positive"),
    NEUTRAL("Neutral"),
    NEGATIVE("Negative");

    private final String code;
    FeedbackType(String code)
    {
        this.code = code;
    }

    public static class Converter extends AbstractEnumConverter<FeedbackType, String> {
        public Converter() {
            super(FeedbackType.class);
        }
    }
}

