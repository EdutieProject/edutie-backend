package com.edutie.backend.infrastucture.external.llm.dto.randomfact;

import com.edutie.backend.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.infrastucture.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RandomFactDto implements ExternalInfrastructureDto<RandomFact, RandomFactGenerationSchema> {
    @JsonProperty
    private String fact;

    @Override
    public RandomFact intoDomainEntity(RandomFactGenerationSchema unused) {
        return new RandomFact(fact);
    }
}
