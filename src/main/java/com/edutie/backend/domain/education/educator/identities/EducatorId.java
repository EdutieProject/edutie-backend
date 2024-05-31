package com.edutie.backend.domain.education.educator.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class EducatorId extends UuidIdentifier {
    public EducatorId() {
        super();
    }

    public EducatorId(UUID uuid) {
        super(uuid);
    }
}
