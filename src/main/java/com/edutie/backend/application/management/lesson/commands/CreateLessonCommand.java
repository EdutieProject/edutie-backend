package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;


@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateLessonCommand extends EducatorAction<CreateLessonCommand> {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull String lessonName;
	private String lessonDescription;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull LessonId previousLessonId;
	private LessonId nextLessonId;

	@Override
	protected CreateLessonCommand getThis() {
		return this;
	}
}
