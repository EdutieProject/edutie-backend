package com.edutie.backend.domain.personalization.learningresource.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class LearningResourceId extends UuidIdentifier {
	public LearningResourceId() {
		super();
	}

	@JsonCreator
	public LearningResourceId(UUID uuid) {
		super(uuid);
	}
}
