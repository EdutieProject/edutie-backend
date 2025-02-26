package com.edutie.domainservice;

import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.personalization.strategy.selectionengine.PersonalizationRuleSelectionEngine;
import com.edutie.domain.service.personalization.learningresource.LearningResourcePersonalizationService;
import com.edutie.domain.service.personalization.learningresource.implementation.LearningResourcePersonalizationServiceImplementation;
import com.edutie.mocks.ExternalServiceMocks;
import com.edutie.mocks.MockUser;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LearningExperiencePersonalizationServiceTests {
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
