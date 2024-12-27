package com.edutie.backend.application.learning.learningresult.queries;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
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
public final class GetLearningResultsSolutionSubmissionQuery extends StudentAction<GetLearningResultsSolutionSubmissionQuery> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningResultId learningResultId;

    @Override
    protected GetLearningResultsSolutionSubmissionQuery getThis() {
        return this;
    }
}
