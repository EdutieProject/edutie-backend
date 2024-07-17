package com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class PersonalizationRuleId extends UuidIdentifier {
    public PersonalizationRuleId() {
        super();
    }
    @JsonCreator
    public PersonalizationRuleId(UUID uuid) {
        super(uuid);
    }
}
