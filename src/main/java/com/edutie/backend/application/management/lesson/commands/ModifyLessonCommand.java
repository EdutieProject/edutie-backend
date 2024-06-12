package com.edutie.backend.application.management.lesson.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class ModifyLessonCommand extends EducatorAction<ModifyLessonCommand> {
    private @NonNull LessonId lessonId;
    private String lessonName;
    private String lessonDescription;
    private LessonId previousLessonId;
    private List<LessonId> nextLessonIds = new ArrayList<>();

    @Override
    protected ModifyLessonCommand getThis() {
        return this;
    }
}
