package com.edutie.domain.core.learning.learningexperience.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningNotesId extends UuidIdentifier {
    public LearningNotesId() {
        super();
    }

    @JsonCreator
    public LearningNotesId(UUID uuid) {
        super(uuid);
    }
}
