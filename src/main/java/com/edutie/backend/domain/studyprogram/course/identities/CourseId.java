package com.edutie.backend.domain.studyprogram.course.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class CourseId extends UuidIdentifier {
    public CourseId() {
        super();
    }

    public CourseId(UUID uuid) {
        super(uuid);
    }
}
