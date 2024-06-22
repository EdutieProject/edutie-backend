package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.application.common.actions.EducatorAction;
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
public class RemoveSegmentCommand extends EducatorAction<RemoveSegmentCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull SegmentId segmentId;

    @Override
    protected RemoveSegmentCommand getThis() {
        return this;
    }
}
