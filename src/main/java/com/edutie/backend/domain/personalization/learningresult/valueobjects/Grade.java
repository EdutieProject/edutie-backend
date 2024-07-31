package com.edutie.backend.domain.personalization.learningresult.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Grade(@JsonValue int gradeNumber) {
    public Grade() {
        this(0);
    }
    public static final Grade MAX_GRADE = new Grade(6);
    public static final Grade MIN_GRADE = new Grade(1);
}
