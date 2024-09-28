package com.edutie.backend.domain.personalization.learningresource.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class ActivityId extends UuidIdentifier {
	public ActivityId() {
		super();
	}

	@JsonCreator
	public ActivityId(UUID uuid) {
		super(uuid);
	}
}
