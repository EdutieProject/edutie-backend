package com.edutie.backend.domain.personalization.learningresource.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class HintId extends UuidIdentifier {
    public HintId() {
        super();
    }

    public HintId(UUID uuid) {
        super(uuid);
    }
}
