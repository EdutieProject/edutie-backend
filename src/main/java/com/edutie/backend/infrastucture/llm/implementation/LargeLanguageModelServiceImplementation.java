package com.edutie.backend.infrastucture.llm.implementation;

import com.edutie.backend.application.learning.ancillaries.schemas.RandomFactGenerationSchema;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import com.edutie.backend.infrastucture.llm.dto.learningresource.LearningResourceCreationDto;
import com.edutie.backend.infrastucture.llm.dto.learningresult.LearningResultCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

@Component
@Slf4j
public class LargeLanguageModelServiceImplementation implements LargeLanguageModelService {

    @Value("${llm-service-host}")
    private String LLM_SERVICE_HOST;

    @Override
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
        final String LEARNING_RESOURCE_LLM_URL = LLM_SERVICE_HOST + "/learning-resource";
        try {
            String serializedBody = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new JavaTimeModule()).writeValueAsString(learningResourceGenerationSchema);
            log.info("===== Sending request to LLM service: ====\nTarget URL: {}\nBody sent: {}", LEARNING_RESOURCE_LLM_URL, serializedBody);
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(LEARNING_RESOURCE_LLM_URL);

                StringEntity entity = new StringEntity(serializedBody, "UTF-8");
                postRequest.setEntity(entity);
                // Set headers (if needed)
                postRequest.setHeader("Content-Type", "application/json; charset=utf-8");
                postRequest.setHeader("Accept", "application/json");

                log.info("POSTREQUEST: " + postRequest.getEntity().toString());
                // Execute the request and get the response
                try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                    // Check the status code
                    int statusCode = response.getStatusLine().getStatusCode();
                    // Get the response body
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    logResponse(statusCode, responseBody);

                    if (statusCode != 200) {
                        return WrapperResult.failureWrapper(LlmServiceErrors.invalidStatus(statusCode, responseBody));
                    }

                    LearningResourceCreationDto learningResourceCreationDto = new ObjectMapper().readValue(responseBody, LearningResourceCreationDto.class);
                    return WrapperResult.successWrapper(learningResourceCreationDto.intoLearningResource(learningResourceGenerationSchema));
                }
            }
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(LlmServiceErrors.exceptionEncountered(ex));
        }
    }

    @Override
    public WrapperResult<LearningResult> generateLearningResult(AssessmentSchema assessmentSchema) {
        final String LEARNING_RESULT_LLM_URL = LLM_SERVICE_HOST + "/learning-result";
        try {
            String serializedBody = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new JavaTimeModule()).writeValueAsString(assessmentSchema);
            log.info("===== Sending request to LLM service: ====\nTarget URL: {}\nBody sent: {}", LEARNING_RESULT_LLM_URL, serializedBody);
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(LEARNING_RESULT_LLM_URL);

                StringEntity entity = new StringEntity(serializedBody, "UTF-8");
                postRequest.setEntity(entity);
                // Set headers (if needed)
                postRequest.setHeader("Content-Type", "application/json; charset=utf-8");
                postRequest.setHeader("Accept", "application/json");

                log.info("POSTREQUEST: " + postRequest.getEntity().toString());
                // Execute the request and get the response
                try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                    // Check the status code
                    int statusCode = response.getStatusLine().getStatusCode();
                    // Get the response body
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    logResponse(statusCode, responseBody);

                    if (statusCode != 200) {
                        return WrapperResult.failureWrapper(LlmServiceErrors.invalidStatus(statusCode, responseBody));
                    }

                    LearningResultCreationDto learningResultCreationDto = new ObjectMapper().readValue(responseBody, LearningResultCreationDto.class);
                    return WrapperResult.successWrapper(learningResultCreationDto.intoLearningResult(assessmentSchema));
                }
            }
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(LlmServiceErrors.exceptionEncountered(ex));
        }
    }

    @Override
    public WrapperResult<RandomFact> generateRandomFact(RandomFactGenerationSchema randomFactGenerationSchema) {
        final String RANDOM_FACT_LLM_URL = LLM_SERVICE_HOST + "/random-fact";
        try {
            String serializedBody = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                    .registerModule(new JavaTimeModule()).writeValueAsString(randomFactGenerationSchema);
            log.info("===== Sending request to LLM service: ====\nTarget URL: {}\nBody sent: {}", RANDOM_FACT_LLM_URL, serializedBody);
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(RANDOM_FACT_LLM_URL);

                StringEntity entity = new StringEntity(serializedBody, "UTF-8");
                postRequest.setEntity(entity);
                // Set headers (if needed)
                postRequest.setHeader("Content-Type", "application/json; charset=utf-8");
                postRequest.setHeader("Accept", "application/json");

                log.info("POSTREQUEST: " + postRequest.getEntity().toString());
                // Execute the request and get the response
                try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                    // Check the status code
                    int statusCode = response.getStatusLine().getStatusCode();
                    // Get the response body
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    logResponse(statusCode, responseBody);

                    if (statusCode != 200) {
                        return WrapperResult.failureWrapper(LlmServiceErrors.invalidStatus(statusCode, responseBody));
                    }

                    RandomFact randomFact = new ObjectMapper().readValue(responseBody, RandomFact.class);
                    return WrapperResult.successWrapper(randomFact);
                }
            }
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(LlmServiceErrors.exceptionEncountered(ex));
        }
    }

    private void logResponse(int statusCode, String responseBody) {
        log.info("Response status: {}", statusCode);
        log.info("Response body: {}", responseBody);
    }
}
