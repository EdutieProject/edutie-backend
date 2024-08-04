package com.edutie.backend.infrastucture.knowledgemap.implementation;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Arrays;
import java.util.List;

@Component
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final String KNOWLEDGE_MAP_URL = "http://wikimapservice/correlations"; //TODO: env prop

    @Override
    public WrapperResult<List<KnowledgeCorrelation>> getKnowledgeCorrelations(KnowledgeSubjectId knowledgeSubjectId) {
        try {
            LOGGER.info("===== Sending request to KM service: ====\n" + "Knowledge subject id: " + knowledgeSubjectId.toString());
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(KNOWLEDGE_MAP_URL); // Replace with your URL

                String serializedBody = new ObjectMapper().writeValueAsString(
                        // Create knowledge sub ref for request body purpose
                        KnowledgeSubjectReference.create(knowledgeSubjectId)
                );
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
