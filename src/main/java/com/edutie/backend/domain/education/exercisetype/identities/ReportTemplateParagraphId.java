package com.edutie.backend.domain.education.exercisetype.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class ReportTemplateParagraphId extends UuidIdentifier {
	public ReportTemplateParagraphId() {
		super();
	}

	@JsonCreator
	public ReportTemplateParagraphId(UUID uuid) {
		super(uuid);
	}
}
