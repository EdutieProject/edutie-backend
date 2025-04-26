package com.edutie.application.management.learningsubject.command;

import com.edutie.application.common.actions.EducatorAction;
import com.edutie.application.common.actions.StudentAction;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateBlankLearningSubjectCommand extends EducatorAction<CreateBlankLearningSubjectCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String learningSubjectName;

	@Override
	protected CreateBlankLearningSubjectCommand getThis() {
		return this;
	}
}
