package com.edutie.backend.domain.personalization.learningresource.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ActivityId extends UuidIdentifier {
    public ActivityId() {
        super();
    }

    public ActivityId(UUID uuid) {
        super(uuid);
    }
}
