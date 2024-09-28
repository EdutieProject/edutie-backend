package com.edutie.backend.domain.personalization.assessmentschema.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class AssessmentSchemaId extends UuidIdentifier {
	public AssessmentSchemaId() {
		super();
	}

	@JsonCreator
	public AssessmentSchemaId(UUID uuid) {
		super(uuid);
	}
}
