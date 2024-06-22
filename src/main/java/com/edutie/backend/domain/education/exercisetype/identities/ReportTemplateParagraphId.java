package com.edutie.backend.domain.education.exercisetype.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ReportTemplateParagraphId extends UuidIdentifier {
    public ReportTemplateParagraphId() {
        super();
    }

    public ReportTemplateParagraphId(UUID uuid) {
        super(uuid);
    }
}
