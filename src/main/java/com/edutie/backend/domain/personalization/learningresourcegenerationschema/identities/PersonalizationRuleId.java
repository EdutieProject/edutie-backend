package com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class PersonalizationRuleId extends UuidIdentifier {
    public PersonalizationRuleId() {
        super();
    }

    public PersonalizationRuleId(UUID uuid) {
        super(uuid);
    }
}
