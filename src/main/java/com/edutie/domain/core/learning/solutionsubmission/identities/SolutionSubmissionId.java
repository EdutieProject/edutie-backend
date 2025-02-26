package com.edutie.domain.core.learning.solutionsubmission.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
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
