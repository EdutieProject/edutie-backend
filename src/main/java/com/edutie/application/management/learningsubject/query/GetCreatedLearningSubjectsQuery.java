package com.edutie.application.management.learningsubject.query;

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
public final class GetCreatedLearningSubjectsQuery extends EducatorAction<GetCreatedLearningSubjectsQuery> {

    @Override
    protected GetCreatedLearningSubjectsQuery getThis() {
        return this;
    }

}
