package com.edutie.backend.domain.education.knowledgesubject.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class KnowledgeSubjectId extends UuidIdentifier {
	public KnowledgeSubjectId() {
		super();
	}

	@JsonCreator
	public KnowledgeSubjectId(UUID uuid) {
		super(uuid);
	}
}
