package com.edutie.backend.domain.personalization.learningresult.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Feedback(
        @Column(columnDefinition = "TEXT") String text
) {
    public Feedback() {
        this("*Feedback could not be generated*");
    }

    public static Feedback of(String text) {
        return new Feedback(text);
    }
}
