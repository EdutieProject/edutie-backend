package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.strategy.FamiliarRemediationStrategy;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.ExternalServiceMocks;
import com.edutie.backend.mocks.LearningHistoryMocker;
import com.edutie.backend.mocks.MockUser;
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
    LearningRequirement learningRequirement;

    @BeforeEach
    public void testSetup() {
        learningRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
    }


    @Test
    public void feedbackRemediationStrategyQualifiesTest() {
        familiarRemediationStrategy = new FamiliarRemediationStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForFamiliarRemediationStrategy(mockUser.getStudentProfile(), learningRequirement, new Grade(2))
        );

        Optional<FamiliarRemediationStrategy.FamiliarRemediationRule> rule = familiarRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningRequirement));

        Assertions.assertTrue(rule.isPresent());
        Assertions.assertEquals(Feedback.of("Hello"), rule.get().getContext());
    }

    @Test
    public void feedbackRemediationStrategyNoQualificationTest() {
        familiarRemediationStrategy = new FamiliarRemediationStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForFamiliarRemediationStrategy(mockUser.getStudentProfile(), learningRequirement, new Grade(4))
        );

        Optional<FamiliarRemediationStrategy.FamiliarRemediationRule> rule = familiarRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningRequirement));

        Assertions.assertFalse(rule.isPresent());
    }
}
