package com.edutie.backend.application.learning.assessment.commands;

import com.edutie.backend.application.common.actions.StudentAction;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
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
public final class AssessSolutionCommand extends StudentAction<AssessSolutionCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningResourceId learningResourceId;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull String solutionSubmissionText;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull int hintsRevealedCount;

    @Override
    protected AssessSolutionCommand getThis() {
        return this;
    }
}
