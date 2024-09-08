package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class ModifySegmentCommand extends EducatorAction<ModifySegmentCommand> {
	private final List<SegmentId> nextSegmentIds = new ArrayList<>();
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull SegmentId segmentId;
	private String segmentName;
	private String segmentSnippetDescription;
	private SegmentId previousSegmentId;

	@Override
	protected ModifySegmentCommand getThis() {
		return this;
	}
}
