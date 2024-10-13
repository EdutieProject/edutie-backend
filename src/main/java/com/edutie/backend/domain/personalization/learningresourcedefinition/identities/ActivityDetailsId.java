package com.edutie.backend.domain.personalization.learningresourcedefinition.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ActivityDetailsId extends UuidIdentifier {
    public ActivityDetailsId() {
        super();
    }

    @JsonCreator
    public ActivityDetailsId(UUID uuid) {
        super(uuid);
    }
}
