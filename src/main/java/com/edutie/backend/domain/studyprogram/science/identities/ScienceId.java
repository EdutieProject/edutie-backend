package com.edutie.backend.domain.studyprogram.science.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ScienceId extends UuidIdentifier {
    public ScienceId() {
        super(UUID.randomUUID());
    }

    @JsonCreator
    public ScienceId(UUID uuid) {
        super(uuid);
    }
}
