package com.edutie.backend.domain.personalization.knowledgesubject;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class KnowledgeSubjectId extends UuidIdentifier {
    public KnowledgeSubjectId() {
        super();
    }

    public KnowledgeSubjectId(UUID uuid) {
        super(uuid);
    }
}
