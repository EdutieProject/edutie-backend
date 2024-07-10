package com.edutie.backend.domain.personalization.solutionsubmission.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SolutionSubmissionId extends UuidIdentifier {
    public SolutionSubmissionId() {
        super();
    }

    public SolutionSubmissionId(UUID uuid) {
        super(uuid);
    }
}
