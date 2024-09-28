package com.edutie.backend.domain.studyprogram.lesson.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class LessonId extends UuidIdentifier {
	public LessonId() {
		super();
	}

	@JsonCreator
	public LessonId(UUID uuid) {
		super(uuid);
	}
}
