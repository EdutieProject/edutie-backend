package com.edutie.domain.personalization;

import com.edutie.domain.core.education.learningrequirement.LearningRequirement;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.personalization.strategy.FamiliarRemediationStrategy;
import com.edutie.infrastructure.external.knowledgemap.KnowledgeMapService;
import com.edutie.mocks.EducationMocks;
import com.edutie.mocks.ExternalServiceMocks;
import com.edutie.mocks.LearningHistoryMocker;
import com.edutie.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class FamiliarRemediationStrategyTests {
    @Autowired
    MockUser mockUser;
    FamiliarRemediationStrategy familiarRemediationStrategy;
    LearningRequirement sourceLearningRequirement;
    LearningRequirement relatedLearningRequirement;
    @Autowired
    KnowledgeMapService knowledgeMapService;

    @BeforeEach
    public void testSetup() {
        sourceLearningRequirement = EducationMocks.independentLearningRequirement(mockUser.getEducatorProfile());
        relatedLearningRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
    }


    @Test
    public void familiarRemediationStrategyQualifiesTest() {
        familiarRemediationStrategy = new FamiliarRemediationStrategy(
                knowledgeMapService,
                LearningHistoryMocker.learningResultPersistenceForFamiliarRemediationStrategy(mockUser.getStudentProfile(), relatedLearningRequirement, Grade.of(6))
        );

        Optional<FamiliarRemediationStrategy.FamiliarRemediationRule> rule = familiarRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(sourceLearningRequirement));

        Assertions.assertTrue(rule.isPresent());
        Assertions.assertEquals(Feedback.of("World"), rule.get().getContext());
    }

    @Test
    public void familiarRemediationStrategyNoQualificationTest() {
        familiarRemediationStrategy = new FamiliarRemediationStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForFamiliarRemediationStrategy(mockUser.getStudentProfile(), relatedLearningRequirement, Grade.of(2))
        );

        Optional<FamiliarRemediationStrategy.FamiliarRemediationRule> rule = familiarRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(relatedLearningRequirement));

        Assertions.assertFalse(rule.isPresent());
    }
}
