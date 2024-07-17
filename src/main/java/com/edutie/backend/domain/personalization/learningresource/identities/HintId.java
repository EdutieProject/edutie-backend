package com.edutie.backend.domain.personalization.learningresource.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class HintId extends UuidIdentifier {
    public HintId() {
        super();
    }
    @JsonCreator
    public HintId(UUID uuid) {
        super(uuid);
    }
}
