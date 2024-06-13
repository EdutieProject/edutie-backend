package com.edutie.backend.domain.studyprogram.course.tag.indentities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;

import java.util.UUID;

public class CourseTagId extends UuidIdentifier {
	public CourseTagId() {
		super();
	}

	public CourseTagId(UUID uuid) {
		super(uuid);
	}
}
