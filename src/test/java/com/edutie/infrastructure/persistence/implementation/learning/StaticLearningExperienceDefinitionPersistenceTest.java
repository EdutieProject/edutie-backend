package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.mocks.LearningResourceMocks;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaticLearningExperienceDefinitionPersistenceTest {
    @Autowired
    MockUser mockUser;
    @Autowired
    LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;

    @BeforeEach
    void setUp() {
        mockUser.saveToPersistence();
    }

    @Test
    void getByAuthorEducator() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = LearningResourceMocks.sampleLearningResourceDefinition(mockUser.getEducatorProfile());
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();

        WrapperResult<List<StaticLearningResourceDefinition>> definitionsWrapper = learningResourceDefinitionPersistence.getByAuthorEducator(mockUser.getEducatorProfile().getId());

        assertTrue(definitionsWrapper.isSuccess());
        assertTrue(definitionsWrapper.getValue().contains(staticLearningResourceDefinition));
    }
}