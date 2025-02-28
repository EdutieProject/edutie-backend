package com.edutie.application.learning.learningresource.queries;

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
public final class GetLearningResourceByIdQuery extends StudentAction<GetLearningResourceByIdQuery> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull LearningExperienceId learningExperienceId;

	@Override
	protected GetLearningResourceByIdQuery getThis() {
		return this;
	}
}
