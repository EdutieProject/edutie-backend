package com.edutie.backend.domain.personalization.learningresource.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class TheoryId extends UuidIdentifier {
    public TheoryId() {
        super();
    }

    @JsonCreator
    public TheoryId(UUID uuid) {
        super(uuid);
    }
}
