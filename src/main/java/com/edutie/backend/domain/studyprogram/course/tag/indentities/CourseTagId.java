package com.edutie.backend.domain.studyprogram.course.tag.indentities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;

import java.util.UUID;

public class CourseTagId extends UuidIdentifier {
	public CourseTagId() {
		super();
	}

	@JsonCreator
	public CourseTagId(UUID uuid) {
		super(uuid);
	}
}
