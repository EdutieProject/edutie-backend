package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class ModifySegmentCommand {
    @JsonIgnore
    private @NonNull UserId educatorUserId;
    private @NonNull SegmentId segmentId;
    private String segmentName;
    private String segmentSnippetDescription;
    private String segmentTheoryDescription;
    private String segmentExerciseDescription;
    private ExerciseTypeId exerciseTypeId;
    private SegmentId previousSegmentId;
    private List<SegmentId> nextSegmentIds = new ArrayList<>();
}
