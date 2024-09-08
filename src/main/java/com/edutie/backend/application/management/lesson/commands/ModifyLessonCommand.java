package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class ModifyLessonCommand extends EducatorAction<ModifyLessonCommand> {
	private final List<LessonId> nextLessonIds = new ArrayList<>();
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	private @NonNull LessonId lessonId;
	private String lessonName;
	private String lessonDescription;
	private LessonId previousLessonId;

	@Override
	protected ModifyLessonCommand getThis() {
		return this;
	}
}
