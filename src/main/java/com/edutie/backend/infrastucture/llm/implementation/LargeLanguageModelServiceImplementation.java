package com.edutie.backend.infrastucture.llm.implementation;

import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import com.edutie.backend.infrastucture.llm.dto.learningresource.LearningResourceCreationDto;
import com.edutie.backend.infrastucture.llm.dto.learningresult.LearningResultCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
public class LargeLanguageModelServiceImplementation implements LargeLanguageModelService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${llm-service-host}")
    private String LLM_SERVICE_HOST;

    @Override
    public WrapperResult<LearningResource> generateLearningResource(LearningResourceGenerationSchema learningResourceGenerationSchema) {
        final String LEARNING_RESOURCE_LLM_URL = LLM_SERVICE_HOST + "/learning-resource";
        try {
            String serializedBody = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(learningResourceGenerationSchema);
            LOGGER.info("===== Sending request to LLM service: ====" +
                    "\nTarget URL: " + LEARNING_RESOURCE_LLM_URL +
                    "\nBody sent: " + serializedBody);
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(LEARNING_RESOURCE_LLM_URL);

                StringEntity entity = new StringEntity(serializedBody);
                postRequest.setEntity(entity);
                // Set headers (if needed)
                postRequest.setHeader("Content-Type", "application/json");
                postRequest.setHeader("Accept", "application/json");

                // Execute the request and get the response
                try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                    // Check the status code
                    int statusCode = response.getStatusLine().getStatusCode();
                    LOGGER.info("Response status: " + statusCode);
                    // Get the response body
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    LOGGER.info("Response body: " + responseBody);

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
    public WrapperResult<LearningResult> assessStudentsSolution(AssessmentSchema assessmentSchema) {
        final String LEARNING_RESULT_LLM_URL = LLM_SERVICE_HOST + "/learning-result";
        try {
            String serializedBody = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(assessmentSchema);
            LOGGER.info("===== Sending request to LLM service: ====" +
                    "\nTarget URL: " + LEARNING_RESULT_LLM_URL +
                    "\nBody sent: " + serializedBody);
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(LEARNING_RESULT_LLM_URL);

                StringEntity entity = new StringEntity(serializedBody);
                postRequest.setEntity(entity);
                // Set headers (if needed)
                postRequest.setHeader("Content-Type", "application/json");
                postRequest.setHeader("Accept", "application/json");

                // Execute the request and get the response
                try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                    // Check the status code
                    int statusCode = response.getStatusLine().getStatusCode();
                    LOGGER.info("Response status: " + statusCode);
                    // Get the response body
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    LOGGER.info("Response body: " + responseBody);

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
}
