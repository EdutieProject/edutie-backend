package com.edutie.backend.application.learning.learningresource.commands;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class AssessSolutionCommand extends StudentAction<AssessSolutionCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull LearningResourceId learningResourceId;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String solutionSubmissionText;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer hintsRevealedCount;

	@Override
	protected AssessSolutionCommand getThis() {
		return this;
	}
}
