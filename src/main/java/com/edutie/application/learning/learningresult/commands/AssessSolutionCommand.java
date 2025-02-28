package com.edutie.application.learning.learningresult.commands;

import com.edutie.application.common.actions.StudentAction;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
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
	private @NonNull LearningExperienceId learningExperienceId;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String solutionSubmissionText;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer hintsRevealedCount;

	@Override
	protected AssessSolutionCommand getThis() {
		return this;
	}
}
