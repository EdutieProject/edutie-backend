package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation.dto;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeSubjectDetailsSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KnowledgeSubjectDetailsDto implements ExternalServiceDto<KnowledgeSubjectDetailsView, GetKnowledgeSubjectDetailsSchema> {
    @JsonProperty
    private String id;
    @JsonProperty
    private String label;
    @JsonProperty
    private String description;

    @Override
    public KnowledgeSubjectDetailsView intoDomainEntity(GetKnowledgeSubjectDetailsSchema schema) {
        return new KnowledgeSubjectDetailsView(
                KnowledgeSubjectReference.create(schema.knowledgeSubjectId()),
                this.label,
                this.description
        );
    }
}
