package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
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
public final class ModifySegmentCommand extends EducatorAction<ModifySegmentCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull SegmentId segmentId;
    private String segmentName;
    private String segmentSnippetDescription;
    private String segmentTheoryDescription;
    private String segmentExerciseDescription;
    private ExerciseTypeId exerciseTypeId;
    private SegmentId previousSegmentId;
    private List<SegmentId> nextSegmentIds = new ArrayList<>();

    @Override
    protected ModifySegmentCommand getThis() {
        return this;
    }
}
