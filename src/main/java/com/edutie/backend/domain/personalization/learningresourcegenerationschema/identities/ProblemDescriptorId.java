package com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ProblemDescriptorId extends UuidIdentifier {
    public ProblemDescriptorId() {
        super();
    }

    public ProblemDescriptorId(UUID uuid) {
        super(uuid);
    }
}
