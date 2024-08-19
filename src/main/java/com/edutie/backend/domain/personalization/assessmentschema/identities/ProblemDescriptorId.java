package com.edutie.backend.domain.personalization.assessmentschema.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ProblemDescriptorId extends UuidIdentifier {
    public ProblemDescriptorId() {
        super();
    }
    @JsonCreator
    public ProblemDescriptorId(UUID uuid) {
        super(uuid);
    }
}
