package com.edutie.application.management.knowledgesubject.query;

import com.edutie.application.common.actions.EducatorAction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class SearchKnowledgeSubjectsQuery extends EducatorAction<SearchKnowledgeSubjectsQuery> {
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String knowledgeSubjectSearchName;

    @Override
    protected SearchKnowledgeSubjectsQuery getThis() {
        return this;
    }

}
