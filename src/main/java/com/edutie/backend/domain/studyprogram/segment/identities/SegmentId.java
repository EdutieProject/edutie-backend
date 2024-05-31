package com.edutie.backend.domain.studyprogram.segment.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SegmentId extends UuidIdentifier {
    public SegmentId() {
        super();
    }

    public SegmentId(UUID uuid) {
        super(uuid);
    }
}
