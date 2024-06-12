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

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreateSegmentCommand extends EducatorAction<CreateSegmentCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull String segmentName;
    private String snippetDescription;
    private String segmentTheoryDescription;
    private String segmentExerciseDescription;
    private ExerciseTypeId exerciseTypeId;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull SegmentId previousSegmentId;
    private SegmentId nextSegmentId;

    @Override
    protected CreateSegmentCommand getThis() {
        return this;
    }
}
