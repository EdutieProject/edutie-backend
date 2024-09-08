package com.edutie.backend.domain.personalization.learningresourcedefinition.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class LearningResourceDefinitionId extends UuidIdentifier {
	public LearningResourceDefinitionId() {
		super();
	}

	@JsonCreator
	public LearningResourceDefinitionId(UUID uuid) {
		super(uuid);
	}
}
