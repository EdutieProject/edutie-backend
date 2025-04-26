package com.edutie.domain.core.learning.learningresult.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningEvaluationId extends UuidIdentifier {
    public LearningEvaluationId() {
        super();
    }

    @JsonCreator
    public LearningEvaluationId(UUID uuid) {
        super(uuid);
    }
}
