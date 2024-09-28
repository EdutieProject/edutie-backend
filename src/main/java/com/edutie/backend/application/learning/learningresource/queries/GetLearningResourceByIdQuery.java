package com.edutie.backend.application.learning.learningresource.queries;

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
public final class GetLearningResourceByIdQuery extends StudentAction<GetLearningResourceByIdQuery> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull LearningResourceId learningResourceId;

	@Override
	protected GetLearningResourceByIdQuery getThis() {
		return this;
	}
}
