package com.edutie.backend.domain.studyprogram.segment.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class SegmentId extends UuidIdentifier {
	public SegmentId() {
		super();
	}

	@JsonCreator
	public SegmentId(UUID uuid) {
		super(uuid);
	}
}
