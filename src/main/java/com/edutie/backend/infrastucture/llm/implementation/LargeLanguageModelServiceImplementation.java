package com.edutie.backend.infrastucture.llm.implementation;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import com.edutie.backend.infrastucture.llm.dto.LearningResourceDetailsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class LargeLanguageModelServiceImplementation implements LargeLanguageModelService {
    @Override
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
        try {
            String serializedBody = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(learningResourceGenerationSchema);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://edutie-llm:10000/learning-resource")) ///* TODO: env property
                    .POST(HttpRequest.BodyPublishers.ofString(serializedBody))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            LearningResourceDetailsDto learningResourceDetailsDto = new ObjectMapper().readValue(response.body(), LearningResourceDetailsDto.class);

            return WrapperResult.successWrapper(learningResourceDetailsDto.intoLearningResource(learningResourceGenerationSchema));
        } catch (IOException e) {
            return WrapperResult.failureWrapper(LlmServiceErrors.connectionError(e));
        } catch (Exception e) {
            return WrapperResult.failureWrapper(LlmServiceErrors.exceptionEncountered(e));
        }
    }
}
