package com.edutie.domain.core.learning.learningexperience.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningResourceId extends UuidIdentifier {
    public LearningResourceId() {
        super();
    }

    @JsonCreator
    public LearningResourceId(UUID uuid) {
        super(uuid);
    }
}
