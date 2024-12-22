package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresource.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private StaticLearningResourceDefinition createAndSaveLearningResourceDefinition() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = StaticLearningResourceDefinition.create(
                mockUser.getEducatorProfile(),
                TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                ActivityDetails.create(PromptFragment.empty(), PromptFragment.empty()),
                Set.of(EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile()))
        );
        learningResourceDefinitionPersistence.save(staticLearningResourceDefinition).throwIfFailure();
        return staticLearningResourceDefinition;
    }

    private LearningResource createAndSaveLearningResource(StaticLearningResourceDefinition staticLearningResourceDefinition) {
        LearningResource learningResource = LearningResource.create(
                mockUser.getStudentProfile(),
                staticLearningResourceDefinition,
                staticLearningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                Activity.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningRequirementId(), "dsadas")),
                new Visualisation("")
        );
        learningResourcePersistence.save(learningResource).throwIfFailure();
        return learningResource;
    }

    @Test
    public void getByLearningResourceDefinitionIdTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningResource learningResource = createAndSaveLearningResource(staticLearningResourceDefinition);

        WrapperResult<List<LearningResource>> learningResourcesWrapper = learningResourcePersistence.getByLearningResourceDefinitionId(staticLearningResourceDefinition.getId());

        assertTrue(learningResourcesWrapper.isSuccess());
        assertTrue(learningResourcesWrapper.getValue().contains(learningResource));
    }
}