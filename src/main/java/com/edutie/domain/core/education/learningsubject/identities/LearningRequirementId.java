package com.edutie.domain.core.education.learningsubject.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningRequirementId extends UuidIdentifier {
    public LearningRequirementId() {
        super();
    }

    @JsonCreator
    public LearningRequirementId(UUID uuid) {
        super(uuid);
    }
}
