package com.edutie.application.management.learningsubject.command;

import com.edutie.application.common.actions.EducatorAction;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
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
public final class SetLearningSubjectKnowledgeSubjectCommand extends EducatorAction<SetLearningSubjectKnowledgeSubjectCommand> {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull LearningSubjectId learningSubjectId;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private @NonNull KnowledgeSubjectId knowledgeSubjectId;

    @Override
    protected SetLearningSubjectKnowledgeSubjectCommand getThis() {
        return this;
    }
}
