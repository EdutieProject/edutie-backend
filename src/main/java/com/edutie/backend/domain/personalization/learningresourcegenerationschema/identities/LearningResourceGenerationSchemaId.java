package com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningResourceGenerationSchemaId extends UuidIdentifier {
    public LearningResourceGenerationSchemaId() {
        super();
    }
    @JsonCreator
    public LearningResourceGenerationSchemaId(UUID uuid) {
        super(uuid);
    }
}
