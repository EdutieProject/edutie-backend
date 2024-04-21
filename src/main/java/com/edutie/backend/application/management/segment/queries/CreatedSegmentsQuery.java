package com.edutie.backend.application.management.segment.queries;

import com.edutie.backend.domain.administration.UserId;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
public final class CreatedSegmentsQuery {
    private @NonNull UserId educatorUserId;
}
