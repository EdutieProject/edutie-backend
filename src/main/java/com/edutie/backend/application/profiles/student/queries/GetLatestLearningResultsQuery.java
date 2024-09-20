package com.edutie.backend.application.profiles.student.queries;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.application.common.actions.StudentAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class GetLatestLearningResultsQuery extends StudentAction<GetLatestLearningResultsQuery> {
	private Integer amount = 5;
	@Override
	protected GetLatestLearningResultsQuery getThis() {
		return this;
	}
}
