package com.edutie.backend.domain.education.educator.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class EducatorId extends UuidIdentifier {
	public EducatorId() {
		super();
	}

	@JsonCreator
	public EducatorId(UUID uuid) {
		super(uuid);
	}
}
