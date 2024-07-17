package com.edutie.backend.domain.personalization.solutionsubmission.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SolutionSubmissionId extends UuidIdentifier {
    public SolutionSubmissionId() {
        super();
    }
    @JsonCreator
    public SolutionSubmissionId(UUID uuid) {
        super(uuid);
    }
}
