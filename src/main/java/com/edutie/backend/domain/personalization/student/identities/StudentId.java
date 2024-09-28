package com.edutie.backend.domain.personalization.student.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class StudentId extends UuidIdentifier {
	public StudentId() {
		super();
	}

	@JsonCreator
	public StudentId(UUID uuid) {
		super(uuid);
	}
}
