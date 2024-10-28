package com.edutie.backend.application.learning;

import com.edutie.backend.application.learning.ancillaries.RandomFactQueryHandler;
import com.edutie.backend.application.learning.ancillaries.implementation.RandomFactQueryHandlerImplementation;
import com.edutie.backend.application.learning.ancillaries.queries.RandomFactQuery;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.mocks.ExternalServiceMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class RandomFactQueryHandlersTests {
    @Autowired
    MockUser mockUser;
    // Handlers
    @Autowired
    private RandomFactQueryHandler randomFactQueryHandler;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
        // Remove mocking out the external services to integration test
        randomFactQueryHandler = new RandomFactQueryHandlerImplementation(
                ExternalServiceMocks.largeLanguageModelServiceMock()
        );
    }


    @Test
    public void getRandomFactTest() {
        RandomFactQuery query = new RandomFactQuery().studentUserId(mockUser.getUserId());

        WrapperResult<RandomFact> queryResult = randomFactQueryHandler.handle(query);

        assertTrue(queryResult.isSuccess());
        assertFalse(queryResult.getValue().fact().isEmpty());
    }

}
