package com.edutie.backend.application.learning.ancillaries.implementation;

import com.edutie.backend.application.learning.ancillaries.RandomFactQueryHandler;
import com.edutie.backend.application.learning.ancillaries.queries.RandomFactQuery;
import com.edutie.backend.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.infrastucture.external.llm.LargeLanguageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class RandomFactQueryHandlerImplementation implements RandomFactQueryHandler {
    private final LargeLanguageModelService largeLanguageModelService;

    @Override
    public WrapperResult<RandomFact> handle(RandomFactQuery randomFactQuery) {
        return largeLanguageModelService.generateRandomFact(new RandomFactGenerationSchema());
    }
}
