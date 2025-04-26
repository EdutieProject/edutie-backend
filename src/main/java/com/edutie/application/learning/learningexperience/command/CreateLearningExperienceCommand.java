package com.edutie.application.learning.learningexperience.command;

import com.edutie.application.common.actions.StudentAction;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
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
public final class CreateLearningExperienceCommand extends StudentAction<CreateLearningExperienceCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningSubjectId learningSubjectId;
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private ElementalRequirementId elementalRequirementId;

    @Override
    protected CreateLearningExperienceCommand getThis() {
        return this;
    }
}
