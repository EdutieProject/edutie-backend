package com.edutie.backend.domain.personalization.learningresult.valueobjects;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Embeddable
public record Grade(
		@JsonValue int gradeNumber) {
	public static final Grade MAX_GRADE = new Grade(6);
	public static final Grade MIN_GRADE = new Grade(1);
	public static final Grade SUCCESS_GRADE = new Grade(5);

	public Grade() {
		this(0);
	}

	public boolean greaterThanOrEqual(Grade grade) {
		return this.gradeNumber >= grade.gradeNumber;
	}
}
