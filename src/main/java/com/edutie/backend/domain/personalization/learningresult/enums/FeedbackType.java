package com.edutie.backend.domain.personalization.learningresult.enums;

import com.edutie.backend.infrastucture.persistence.config.AbstractEnumConverter;
import com.edutie.backend.domain.common.enums.PersistableEnum;
import lombok.Getter;

/**
 * Enum describing general opinion described by feedback.
 * Serves as a one-off alternative for user to know about
 * feedback opinion.
 */
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
    public static FeedbackType fromString(String code) {
        for (FeedbackType feedbackType : NEUTRAL.getDeclaringClass().getEnumConstants()) {
            if (feedbackType.code.equals(code))
                return feedbackType;
        }
        return NEUTRAL;
    }
}

