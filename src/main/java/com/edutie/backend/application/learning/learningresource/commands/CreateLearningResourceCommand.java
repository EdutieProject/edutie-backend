package com.edutie.backend.application.learning.learningresource.commands;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateLearningResourceCommand extends StudentAction<CreateLearningResourceCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull LearningResourceDefinitionId learningResourceDefinitionId;

	@Override
	protected CreateLearningResourceCommand getThis() {
		return this;
	}
}
