package com.edutie.backend.domain.education.learningrequirement.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class KnowledgeNodeId extends UuidIdentifier {
    public KnowledgeNodeId() {
        super();
    }

    public KnowledgeNodeId(UUID uuid) {
        super(uuid);
    }
}
