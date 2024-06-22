package com.edutie.backend.application.management.segment.queries;

import com.edutie.backend.application.common.actions.EducatorAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class CreatedSegmentsQuery extends EducatorAction<CreatedSegmentsQuery> {
    @Override
    protected CreatedSegmentsQuery getThis() {
        return this;
    }
}
