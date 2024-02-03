package com.edutie.edutiebackend.domain.core.learningresult.valueobjects;

import com.edutie.edutiebackend.domain.core.learningresult.enums.FeedbackType;
import jakarta.persistence.Embeddable;

@Embeddable
public record Feedback(
        String text,
        FeedbackType type
) {
    public Feedback() {
        this("Feedback could not be generated", FeedbackType.NEUTRAL);
    }
}
