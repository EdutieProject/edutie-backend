package com.edutie.infrastructure.knowledgemap.learningsubject.implementation.dto;

import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.knowledgemap.learningsubject.schema.StudentObjectiveGenerationSchema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentObjectiveDto implements ExternalServiceDto<String, StudentObjectiveGenerationSchema> {
    @JsonProperty
    private String objective;

    @JsonCreator
    public StudentObjectiveDto(String objective) {
        this.objective = objective;
    }


    @Override
    public String intoDomainEntity(StudentObjectiveGenerationSchema studentObjectiveGenerationSchema) {
        return this.objective;
    }
}
