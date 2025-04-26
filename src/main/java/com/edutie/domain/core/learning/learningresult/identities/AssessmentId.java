package com.edutie.domain.core.learning.learningresult.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AssessmentId extends UuidIdentifier {
    public AssessmentId() {
        super();
    }

    @JsonCreator
    public AssessmentId(UUID uuid) {
        super(uuid);
    }
}
