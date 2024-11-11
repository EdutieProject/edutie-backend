package com.edutie.backend.domainservice;

import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.strategy.selectionengine.PersonalizationRuleSelectionEngine;
import com.edutie.backend.domainservice.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.backend.domainservice.personalization.learningresource.implementation.LearningResourcePersonalizationServiceImplementation;
import com.edutie.backend.mocks.ExternalServiceMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LearningResourcePersonalizationServiceTests {
    @Autowired
    private MockUser mockUser;

    @Autowired
    private LearningResultPersistence learningResultPersistence;

    @Autowired
    private PersonalizationRuleSelectionEngine personalizationRuleSelectionEngine;

    private LearningResourcePersonalizationService learningResourcePersonalizationService = new LearningResourcePersonalizationServiceImplementation(
            learningResultPersistence,
            ExternalServiceMocks.largeLanguageModelServiceMock(),
            personalizationRuleSelectionEngine
    );

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
    }

    @Test
    public void personalizationSchemaTest() {
        //TODO after personalization v 2 implementation
    }

    @Test
    public void personalizationServiceTest() {
        //TODO after personalization v 2 implementation
    }
}
