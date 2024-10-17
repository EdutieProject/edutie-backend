package com.edutie.backend.application.management.learningresourcedefinition.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.util.Set;


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
	private Set<LearningRequirementId> learningRequirementIds;
	private SegmentId segmentId;

	@Override
	protected CreateLearningResourceDefinitionCommand getThis() {
		return this;
	}
}
