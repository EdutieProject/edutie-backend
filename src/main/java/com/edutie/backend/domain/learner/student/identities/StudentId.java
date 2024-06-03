package com.edutie.backend.domain.learner.student.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class StudentId extends UuidIdentifier {
    public StudentId() {
        super();
    }

    public StudentId(UUID uuid) {
        super(uuid);
    }
}
