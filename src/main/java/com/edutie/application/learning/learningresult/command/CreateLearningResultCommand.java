package com.edutie.application.learning.learningresult.command;

import com.edutie.application.common.actions.StudentAction;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
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
public final class CreateLearningResultCommand<T extends SolutionSubmission> extends StudentAction<CreateLearningResultCommand<T>> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningExperienceId learningExperienceId;
    private @NonNull T solutionSubmission;

    @Override
    protected CreateLearningResultCommand<T> getThis() {
        return this;
    }
}
