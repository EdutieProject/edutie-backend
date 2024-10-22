package com.edutie.backend.infrastucture.external.knowledgemap.implementation;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.infrastucture.external.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.external.knowledgemap.dto.KnowledgeCorrelationCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {

    @Value("${knowledge-map-host}")
    private String KNOWLEDGE_MAP_HOST;


    @Override
    public WrapperResult<Set<KnowledgeCorrelation>> getKnowledgeCorrelations(Set<KnowledgeSubjectId> knowledgeSubjectIds) {
        final String CORRELATIONS_URL = KNOWLEDGE_MAP_HOST + "/correlations";
        try {
            log.info("===== Sending request to KM service: ====\nTarget HOST: {}\nKnowledge subject id: {}", CORRELATIONS_URL, knowledgeSubjectIds.toString());
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(CORRELATIONS_URL); // Replace with your URL

                String serializedBody = new ObjectMapper().writeValueAsString(knowledgeSubjectIds);
                StringEntity entity = new StringEntity(serializedBody);
                postRequest.setEntity(entity);
                // Set headers (if needed)
                postRequest.setHeader("Content-Type", "application/json");
                postRequest.setHeader("Accept", "application/json");

                // Execute the request and get the response
                try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                    // Check the status code
                    int statusCode = response.getStatusLine().getStatusCode();
                    log.info("Response status: {}", statusCode);
                    // Get the response body
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    log.info("Response body: {}", responseBody);

                    if (statusCode != 200) {
                        return WrapperResult.failureWrapper(KnowledgeMapServiceErrors.invalidStatus(statusCode, responseBody));
                    }

                    KnowledgeCorrelationCreationDto[] knowledgeCorrelations = new ObjectMapper().readValue(responseBody, KnowledgeCorrelationCreationDto[].class);
                    return WrapperResult.successWrapper(
                            Arrays.stream(knowledgeCorrelations).map(KnowledgeCorrelationCreationDto::intoKnowledgeCorrelation).collect(Collectors.toSet())
                    );
                }
            }
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(KnowledgeMapServiceErrors.exceptionEncountered(ex));
        }
    }
}
