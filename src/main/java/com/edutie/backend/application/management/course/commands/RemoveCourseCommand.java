package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class RemoveCourseCommand extends EducatorAction<RemoveCourseCommand> {
	@Schema(example = "false")
	private final boolean removeLessons = false;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull CourseId courseId;

	@Override
	protected RemoveCourseCommand getThis() {
		return this;
	}
}
