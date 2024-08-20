package com.edutie.backend.domain.personalization.learningresult.valueobjects;

import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

@Embeddable
public record Feedback(
        String text,
        @Convert(converter = FeedbackType.Converter.class) FeedbackType type
) {
    public Feedback() {
        this("Feedback could not be generated", FeedbackType.NEUTRAL);
    }
}
