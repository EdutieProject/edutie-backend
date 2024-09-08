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
public final class ModifyCourseCommand extends EducatorAction<ModifyCourseCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull CourseId courseId;
	private String courseName;
	private String courseDescription;
	private Boolean accessibility;

	@Override
	protected ModifyCourseCommand getThis() {
		return this;
	}
}
