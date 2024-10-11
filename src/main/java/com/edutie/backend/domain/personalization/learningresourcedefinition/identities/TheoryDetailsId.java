package com.edutie.backend.domain.personalization.learningresourcedefinition.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class TheoryDetailsId extends UuidIdentifier {
	public TheoryDetailsId() {
		super();
	}

	@JsonCreator
	public TheoryDetailsId(UUID uuid) {
		super(uuid);
	}
}
