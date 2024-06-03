package com.edutie.backend.domain.personalization.learningresource.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningResourceId extends UuidIdentifier {
    public LearningResourceId() {
        super();
    }

    public LearningResourceId(UUID uuid) {
        super(uuid);
    }
}
