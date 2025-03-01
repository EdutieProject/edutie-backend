package com.edutie.application.management.learningsubject.command;

import com.edutie.application.common.actions.EducatorAction;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
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
public final class AddLearningSubjectRequirementCommand extends EducatorAction<AddLearningSubjectRequirementCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningSubjectId learningSubjectId;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull String requirementTitle;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull Integer requirementOrdinal;

    @Override
    protected AddLearningSubjectRequirementCommand getThis() {
        return this;
    }
}
