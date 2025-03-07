package com.edutie.infrastructure.knowledgemap.knowledgesubject.implementation.dto;

import com.edutie.application.management.knowledgesubject.view.KnowledgeSubjectSearchView;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.KnowledgeSubjectSearchSchema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class KnowledgeSubjectSearchResultsDto implements ExternalServiceDto<List<KnowledgeSubjectSearchView>, KnowledgeSubjectSearchSchema> {
    @Getter
    public static class KnowledgeSubjectSearchResult {
        @JsonProperty
        private String id;
        @JsonProperty
        private String label;
        @JsonProperty
        private String description;
    }
    @JsonIgnore
    private List<KnowledgeSubjectSearchResult> knowledgeSubjectSearchDtos;

    @JsonCreator
    public KnowledgeSubjectSearchResultsDto(List<KnowledgeSubjectSearchResult> knowledgeSubjectSearchDtos) {
        this.knowledgeSubjectSearchDtos = knowledgeSubjectSearchDtos;
    }

    @Override
    public List<KnowledgeSubjectSearchView> intoDomainEntity(KnowledgeSubjectSearchSchema knowledgeSubjectSearchSchema) {
        return this.knowledgeSubjectSearchDtos.stream().map(o ->
                new KnowledgeSubjectSearchView(KnowledgeSubjectReference.create(new KnowledgeSubjectId(o.getId())), o.getLabel(), o.getDescription())
        ).toList();
    }
}
