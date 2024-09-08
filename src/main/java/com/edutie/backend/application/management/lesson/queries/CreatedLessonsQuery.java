package com.edutie.backend.application.management.lesson.queries;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreatedLessonsQuery extends EducatorAction<CreatedLessonsQuery> {
	@Override
	protected CreatedLessonsQuery getThis() {
		return this;
	}
}
