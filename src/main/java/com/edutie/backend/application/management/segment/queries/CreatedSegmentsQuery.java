package com.edutie.backend.application.management.segment.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.NonNull;

public record CreatedSegmentsQuery(
        @NonNull UserId educatorUserId
) {
}
