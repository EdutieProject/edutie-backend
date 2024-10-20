package com.edutie.backend.domain.personalization.learningresult.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
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
