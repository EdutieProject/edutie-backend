package com.edutie.backend.application.learning.studyprogram.queries;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class ViewLessonsFromCourseQuery extends StudentAction<ViewLessonsFromCourseQuery> {
	private @NonNull CourseId courseId;

	@Override
	protected ViewLessonsFromCourseQuery getThis() {
		return this;
	}
}
