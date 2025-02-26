package com.edutie.domain.core.learning.student.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class StudentId extends UuidIdentifier {
    public StudentId() {
        super();
    }

    @JsonCreator
    public StudentId(UUID uuid) {
        super(uuid);
    }
}
