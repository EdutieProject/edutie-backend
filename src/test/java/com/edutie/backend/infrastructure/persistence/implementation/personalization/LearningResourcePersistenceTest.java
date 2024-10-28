package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LearningResourcePersistenceTest {
    @Autowired
    MockUser mockUser;
    @Autowired
    LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    LearningResultPersistence learningResultPersistence;
    @Autowired
    LearningResourcePersistence learningResourcePersistence;

    @BeforeEach
    public void testSetup() {
        mockUser.saveToPersistence();
    }

    private LearningResourceDefinition createAndSaveLearningResourceDefinition() {
        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of(EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile()))
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition).throwIfFailure();
        return learningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(LearningResourceDefinition learningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                LearningResourceGenerationSchema.create(mockUser.getStudentProfile(), learningResultPersistence, Set.of(), learningResourceDefinition),
                Activity.create("", Set.of()),
                Theory.create("", "")
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void getByLearningResourceDefinitionIdTest() {
        LearningResourceDefinition learningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(learningResourceDefinition);

        WrapperResult<List<LearningResource>> learningResourcesWrapper = learningResourcePersistence.getByLearningResourceDefinitionId(learningResourceDefinition.getId());

        assertTrue(learningResourcesWrapper.isSuccess());
        assertTrue(learningResourcesWrapper.getValue().contains(learningResource));
    }
}