package com.edutie.domain.core.education.educator.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class EducatorId extends UuidIdentifier {
    public EducatorId() {
        super();
    }

    @JsonCreator
    public EducatorId(UUID uuid) {
        super(uuid);
    }
}
