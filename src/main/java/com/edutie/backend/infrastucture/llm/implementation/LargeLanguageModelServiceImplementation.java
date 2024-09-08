package com.edutie.backend.infrastucture.llm.implementation;

import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.infrastucture.llm.LargeLanguageModelService;
import com.edutie.backend.infrastucture.llm.dto.learningresource.LearningResourceCreationDto;
import com.edutie.backend.infrastucture.llm.dto.learningresult.LearningResultCreationDto;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import validation.WrapperResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import lombok.extern.slf4j.*;

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

				StringEntity entity = new StringEntity(serializedBody);
				postRequest.setEntity(entity);
				// Set headers (if needed)
				postRequest.setHeader("Content-Type", "application/json");
				postRequest.setHeader("Accept", "application/json");

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

				StringEntity entity = new StringEntity(serializedBody);
				postRequest.setEntity(entity);
				// Set headers (if needed)
				postRequest.setHeader("Content-Type", "application/json");
				postRequest.setHeader("Accept", "application/json");

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

	private void logResponse(int statusCode, String responseBody) {
		log.info("Response status: {}", statusCode);
		log.info("Response body: {}", responseBody);
	}
}
