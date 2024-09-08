package com.edutie.backend.application.management.course.queries;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreatedCoursesQuery extends EducatorAction<CreatedCoursesQuery> {
	@Override
	protected CreatedCoursesQuery getThis() {
		return this;
	}
}
