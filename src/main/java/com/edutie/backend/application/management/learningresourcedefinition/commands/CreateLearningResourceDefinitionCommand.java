package com.edutie.backend.application.management.learningresourcedefinition.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;


@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateLearningResourceDefinitionCommand extends EducatorAction<CreateLearningResourceDefinitionCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String theoryDescription;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String exerciseDescription;
	private String additionalSummaryDescription;
	private String additionalHintsDescription;
	private SegmentId segmentId;

	@Override
	protected CreateLearningResourceDefinitionCommand getThis() {
		return this;
	}
}
