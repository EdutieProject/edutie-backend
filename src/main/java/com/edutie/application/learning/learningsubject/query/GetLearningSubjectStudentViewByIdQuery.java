package com.edutie.application.learning.learningsubject.query;

import com.edutie.application.common.actions.StudentAction;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
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
public final class GetLearningSubjectStudentViewByIdQuery extends StudentAction<GetLearningSubjectStudentViewByIdQuery> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningSubjectId learningSubjectId;

    @Override
    protected GetLearningSubjectStudentViewByIdQuery getThis() {
        return this;
    }

}
