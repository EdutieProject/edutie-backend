package com.edutie.backend.domain.education.exercisetype.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class ExerciseTypeId extends UuidIdentifier {
	public ExerciseTypeId() {
		super();
	}

	@JsonCreator
	public ExerciseTypeId(UUID uuid) {
		super(uuid);
	}
}
