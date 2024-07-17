package com.edutie.backend.domain.personalization.learningresult.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Grade(@JsonValue int gradeNumber) {
    public Grade() {
        this(0);
    }
}
