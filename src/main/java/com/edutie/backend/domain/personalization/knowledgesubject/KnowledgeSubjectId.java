package com.edutie.backend.domain.personalization.knowledgesubject;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class KnowledgeSubjectId extends UuidIdentifier {
    public KnowledgeSubjectId() {
        super();
    }
    @JsonCreator
    public KnowledgeSubjectId(UUID uuid) {
        super(uuid);
    }
}
