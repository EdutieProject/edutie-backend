package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
    private @NonNull String segmentName;
    private String snippetDescription;
    private String segmentTheoryDescription;
    private String segmentExerciseDescription;
    private ExerciseTypeId exerciseTypeId;
    private @NonNull SegmentId previousSegmentId;
    private SegmentId nextSegmentId;

    @Override
    protected CreateSegmentCommand getThis() {
        return this;
    }
}
