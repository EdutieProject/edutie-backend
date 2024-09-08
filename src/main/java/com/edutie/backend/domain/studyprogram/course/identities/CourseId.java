package com.edutie.backend.domain.studyprogram.course.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class CourseId extends UuidIdentifier {
	public CourseId() {
		super();
	}

	@JsonCreator
	public CourseId(UUID uuid) {
		super(uuid);
	}
}
