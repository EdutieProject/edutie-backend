package com.edutie.backend.infrastucture.knowledgemap.implementation;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.fasterxml.jackson.databind.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import validation.WrapperResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import lombok.extern.slf4j.*;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {

	@Value("${knowledge-map-host}")
	private String KNOWLEDGE_MAP_HOST;


	@Override
	public WrapperResult<List<KnowledgeCorrelation>> getKnowledgeCorrelations(KnowledgeSubjectId knowledgeSubjectId) {
		final String CORRELATIONS_URL = KNOWLEDGE_MAP_HOST + "/correlations";
		try {
			log.info("===== Sending request to KM service: ====\nTarget HOST: {}\nKnowledge subject id: {}", CORRELATIONS_URL, knowledgeSubjectId.toString());
			// Create an instance of HttpClient
			try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
				// Create a POST request with the target URL
				HttpPost postRequest = new HttpPost(CORRELATIONS_URL); // Replace with your URL

				String serializedBody = new ObjectMapper().writeValueAsString(
						// Create knowledge sub ref for request body purpose
						KnowledgeSubjectReference.create(knowledgeSubjectId));
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

					KnowledgeCorrelation[] knowledgeCorrelations = new ObjectMapper().readValue(responseBody, KnowledgeCorrelation[].class);
					return WrapperResult.successWrapper(Arrays.stream(knowledgeCorrelations).toList());
				}
			}
		} catch (Exception ex) {
			return WrapperResult.failureWrapper(KnowledgeMapServiceErrors.exceptionEncountered(ex));
		}
	}
}
