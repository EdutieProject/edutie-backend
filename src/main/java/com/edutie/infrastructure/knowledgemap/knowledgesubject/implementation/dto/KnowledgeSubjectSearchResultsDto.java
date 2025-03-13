package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation.dto;

import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.KnowledgeSubjectSearchSchema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class KnowledgeSubjectSearchResultsDto implements ExternalServiceDto<List<KnowledgeSubjectDetailsView>, KnowledgeSubjectSearchSchema> {
    @JsonIgnore
    private List<KnowledgeSubjectDetailsDto> knowledgeSubjectSearchDtos;

    @JsonCreator
    public KnowledgeSubjectSearchResultsDto(List<KnowledgeSubjectDetailsDto> knowledgeSubjectSearchDtos) {
        this.knowledgeSubjectSearchDtos = knowledgeSubjectSearchDtos;
    }

    @Override
    public List<KnowledgeSubjectDetailsView> intoDomainEntity(KnowledgeSubjectSearchSchema knowledgeSubjectSearchSchema) {
        return this.knowledgeSubjectSearchDtos.stream().map(o ->
                new KnowledgeSubjectDetailsView(KnowledgeSubjectReference.create(new KnowledgeSubjectId(o.getId())), o.getLabel(), o.getDescription())
        ).toList();
    }
}
