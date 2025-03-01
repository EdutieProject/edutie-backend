package com.edutie.application.learning.learningexperience.query;

import com.edutie.application.common.actions.StudentAction;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
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
public final class GetLearningExperienceByIdQuery extends StudentAction<GetLearningExperienceByIdQuery> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningExperienceId learningExperienceId;

    @Override
    protected GetLearningExperienceByIdQuery getThis() {
        return this;
    }

}
