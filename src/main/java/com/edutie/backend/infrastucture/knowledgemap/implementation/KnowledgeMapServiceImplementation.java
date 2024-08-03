package com.edutie.backend.infrastucture.knowledgemap.implementation;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
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
import java.util.Arrays;
import java.util.List;

@Component
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {
    private final boolean MOCKED = true;
    @Override
    public WrapperResult<List<KnowledgeCorrelation>> getKnowledgeCorrelations(KnowledgeSubjectId knowledgeSubjectId) {
        try {
            if (MOCKED)
                return WrapperResult.successWrapper(List.of());

            String serializedBody = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(KnowledgeSubjectReference.create(knowledgeSubjectId));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://edutie-knowledge-map:10000/correlations")) ///* TODO: env property
                    .POST(HttpRequest.BodyPublishers.ofString(serializedBody))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            KnowledgeCorrelation[] knowledgeCorrelations = new ObjectMapper().readValue(response.body(), KnowledgeCorrelation[].class);

            return WrapperResult.successWrapper(Arrays.stream(knowledgeCorrelations).toList());
        } catch (IOException e) {
            return WrapperResult.failureWrapper(KnowledgeMapServiceErrors.connectionError(e));
        } catch (Exception e) {
            return WrapperResult.failureWrapper(KnowledgeMapServiceErrors.exceptionEncountered(e));
        }
    }
}
