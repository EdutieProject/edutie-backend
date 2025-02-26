package com.edutie.application.learning.learningresource.queries;

import com.edutie.application.common.actions.StudentAction;
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
public final class GetLatestLearningResourcesForStudentQuery extends StudentAction<GetLatestLearningResourcesForStudentQuery> {

    @Override
    protected GetLatestLearningResourcesForStudentQuery getThis() {
        return this;
    }
}
