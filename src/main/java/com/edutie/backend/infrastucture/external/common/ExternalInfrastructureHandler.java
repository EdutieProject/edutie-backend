package com.edutie.backend.infrastucture.external.common;

import com.edutie.backend.infrastucture.external.common.dto.ExternalInfrastructureDto;
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
import validation.WrapperResult;

/**
 * The handler of external infrastructure services.
 *
 * @param <TOutputDomainEntity>      Output domain entity that is retrieved using the handler
 * @param <TExternalServiceRequest>  The entity that will be serialized and sent to the external service as a request body
 * @param <TExternalServiceResponse> The DTO that will be received as a response from the External Service
 */
@Slf4j
public class ExternalInfrastructureHandler<TOutputDomainEntity, TExternalServiceRequest, TExternalServiceResponse extends ExternalInfrastructureDto<TOutputDomainEntity, TExternalServiceRequest>> {
    private final Class<? extends ExternalService> externalServiceClass;
    private String actionUrl = null;
    private final ObjectMapper requestObjectMapper = new ObjectMapper();

    public ExternalInfrastructureHandler(Class<? extends ExternalService> externalServiceClass) {
        this.externalServiceClass = externalServiceClass;
    }

    public ExternalInfrastructureHandler<TOutputDomainEntity, TExternalServiceRequest, TExternalServiceResponse> setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
        return this;
    }

    public ExternalInfrastructureHandler<TOutputDomainEntity, TExternalServiceRequest, TExternalServiceResponse> disableSerializationFeatures(SerializationFeature... serializationFeatures) {
        for (SerializationFeature serializationFeature : serializationFeatures) {
            requestObjectMapper.disable(serializationFeature);
        }
        return this;
    }

    public WrapperResult<TOutputDomainEntity> handle(TExternalServiceRequest requestBody, Class<TExternalServiceResponse> responseClass) {
        try {
            String serializedBody = requestObjectMapper
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(requestBody);

            log.info("===== Sending request to LLM service: ====\nTarget URL: {}\nBody sent: {}", actionUrl, serializedBody);
            // Create an instance of HttpClient
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Create a POST request with the target URL
                HttpPost postRequest = new HttpPost(actionUrl);

                StringEntity entity = new StringEntity(serializedBody, "UTF-8");
                postRequest.setEntity(entity);
                // Set headers (if needed)
                postRequest.setHeader("Content-Type", "application/json; charset=utf-8");
                postRequest.setHeader("Accept", "application/json");

                log.info("POST REQUEST: {}", postRequest.getEntity().toString());
                // Execute the request and get the response
                try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                    // Check the status code
                    int statusCode = response.getStatusLine().getStatusCode();
                    // Get the response body
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    logResponse(statusCode, responseBody);

                    if (statusCode != 200) {
                        return WrapperResult.failureWrapper(ExternalServiceErrors.invalidStatus(externalServiceClass, statusCode, responseBody));
                    }

                    TExternalServiceResponse externalServiceResponse = new ObjectMapper().readValue(responseBody, responseClass);
                    return WrapperResult.successWrapper(externalServiceResponse.intoDomainEntity(requestBody));
                }
            }
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(ExternalServiceErrors.exceptionEncountered(externalServiceClass, ex));
        }
    }

    private void logResponse(int statusCode, String responseBody) {
        log.info("Response status: {}", statusCode);
        log.info("Response body: {}", responseBody);
    }
}
