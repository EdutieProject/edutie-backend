package com.edutie.backend.application.learning.learningresource.commands;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DynamicContextType;
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
public final class CreateDynamicLearningResourceCommand extends StudentAction<CreateDynamicLearningResourceCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String contextText;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private DynamicContextType contextType;

	@Override
	protected CreateDynamicLearningResourceCommand getThis() {
		return this;
	}
}
