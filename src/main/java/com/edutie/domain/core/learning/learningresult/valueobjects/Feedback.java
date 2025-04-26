package com.edutie.domain.core.learning.learningresult.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Feedback(
        @Column(columnDefinition = "TEXT") String text
) {
    public Feedback() {
        this("");
    }

    public static Feedback of(String text) {
        return new Feedback(text);
    }
}
