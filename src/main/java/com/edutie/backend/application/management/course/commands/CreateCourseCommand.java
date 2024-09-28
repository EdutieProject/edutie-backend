package com.edutie.backend.application.management.course.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateCourseCommand extends EducatorAction<CreateCourseCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String courseName;
	private String courseDescription;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull ScienceId scienceId;

	@Override
	protected CreateCourseCommand getThis() {
		return this;
	}
}
