package com.edutie.backend.domain.personalization.learningresult.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class LearningResultId extends UuidIdentifier {
	public LearningResultId() {
		super();
	}

	@JsonCreator
	public LearningResultId(UUID uuid) {
		super(uuid);
	}
}
