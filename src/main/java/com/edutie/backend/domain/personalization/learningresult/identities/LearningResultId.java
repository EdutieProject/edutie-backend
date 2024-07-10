package com.edutie.backend.domain.personalization.learningresult.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningResultId extends UuidIdentifier {
    public LearningResultId() {
        super();
    }

    public LearningResultId(UUID uuid) {
        super(uuid);
    }
}
