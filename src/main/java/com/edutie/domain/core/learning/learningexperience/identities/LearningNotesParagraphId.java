package com.edutie.domain.core.learning.learningexperience.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningNotesParagraphId extends UuidIdentifier {
    public LearningNotesParagraphId() {
        super();
    }

    @JsonCreator
    public LearningNotesParagraphId(UUID uuid) {
        super(uuid);
    }
}
