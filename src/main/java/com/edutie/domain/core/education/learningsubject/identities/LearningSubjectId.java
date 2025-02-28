package com.edutie.domain.core.education.learningsubject.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningSubjectId extends UuidIdentifier {
    public LearningSubjectId() {
        super();
    }

    @JsonCreator
    public LearningSubjectId(UUID uuid) {
        super(uuid);
    }
}
