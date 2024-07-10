package com.edutie.backend.domain.personalization.learningresult.valueobjects;

import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
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
