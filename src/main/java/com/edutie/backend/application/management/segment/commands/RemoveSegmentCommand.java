package com.edutie.backend.application.management.segment.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class RemoveSegmentCommand{
        private @NonNull UserId educatorUserId;
        private @NonNull SegmentId segmentId;
}
