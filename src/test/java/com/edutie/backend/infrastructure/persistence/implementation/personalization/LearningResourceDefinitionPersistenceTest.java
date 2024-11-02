package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.LearningResourceMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LearningResourceDefinitionPersistenceTest {
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
        LearningResourceDefinition learningResourceDefinition = LearningResourceMocks.sampleLearningResourceDefinition(mockUser.getEducatorProfile());
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();

        WrapperResult<List<LearningResourceDefinition>> definitionsWrapper = learningResourceDefinitionPersistence.getByAuthorEducator(mockUser.getEducatorProfile().getId());

        assertTrue(definitionsWrapper.isSuccess());
        assertTrue(definitionsWrapper.getValue().contains(learningResourceDefinition));
    }
}