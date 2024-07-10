package com.edutie.backend.domain.personalization.learningresult.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Grade(int gradeNumber) {
    public Grade() {
        this(0);
    }
}
