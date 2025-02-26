package com.edutie.infrastructure.external.llm.dto.randomfact;

import com.edutie.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RandomFactDto implements ExternalInfrastructureDto<RandomFact, RandomFactGenerationSchema> {
    @JsonProperty
    private String fact;

    @Override
    public RandomFact intoDomainEntity(RandomFactGenerationSchema unused) {
        return new RandomFact(fact);
    }
}
