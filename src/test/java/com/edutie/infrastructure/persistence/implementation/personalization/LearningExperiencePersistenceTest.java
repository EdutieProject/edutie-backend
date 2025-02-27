package com.edutie.infrastructure.persistence.implementation.personalization;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.mocks.EducationMocks;
import com.edutie.mocks.MockUser;
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
class LearningExperiencePersistenceTest {
    @Autowired
    MockUser mockUser;
    @Autowired
    LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    @Autowired
    LearningResultPersistence learningResultPersistence;
    @Autowired
    LearningExperiencePersistence learningExperiencePersistence;

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

    private LearningExperience createAndSaveLearningResource(StaticLearningResourceDefinition staticLearningResourceDefinition) {
        LearningExperience learningExperience = LearningExperience.create(
                mockUser.getStudentProfile(),
                staticLearningResourceDefinition,
                staticLearningResourceDefinition.getLearningRequirements().stream()
                        .flatMap(o -> o.getElementalRequirements().stream()).filter(o -> o.getOrdinal() < 1).collect(Collectors.toSet()),
                ActivityBase.create("Activity text", Set.of(Hint.create("aaa"))),
                Set.of(TheoryCard.create(new LearningRequirementId(), "dsadas")),
                new Visualisation("")
        );
        learningExperiencePersistence.save(learningExperience).throwIfFailure();
        return learningExperience;
    }

    @Test
    public void getByLearningResourceDefinitionIdTest() {
        StaticLearningResourceDefinition staticLearningResourceDefinition = createAndSaveLearningResourceDefinition();
        LearningExperience learningExperience = createAndSaveLearningResource(staticLearningResourceDefinition);

        WrapperResult<List<LearningExperience>> learningResourcesWrapper = learningExperiencePersistence.getByLearningResourceDefinitionId(staticLearningResourceDefinition.getId());

        assertTrue(learningResourcesWrapper.isSuccess());
        assertTrue(learningResourcesWrapper.getValue().contains(learningExperience));
    }
}