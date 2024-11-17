package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.strategy.RecommendationStrategy;
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
public class RecommendationStrategyTests {
    @Autowired
    MockUser mockUser;
    RecommendationStrategy recommendationStrategy;
    LearningRequirement learningRequirement;

    @BeforeEach
    public void testSetup() {
        learningRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
    }


    @Test
    public void recommendationStrategyQualifiesTest() {
        recommendationStrategy = new RecommendationStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForRecommendationStrategy(mockUser.getStudentProfile(), learningRequirement, new Grade(5))
        );

        Optional<RecommendationStrategy.RecommendationRule> rule = recommendationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningRequirement));

        Assertions.assertTrue(rule.isPresent());
    }

    @Test
    public void recommendationStrategyNoQualificationTest() {
        recommendationStrategy = new RecommendationStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForRecommendationStrategy(mockUser.getStudentProfile(), learningRequirement, new Grade(2))
        );

        Optional<RecommendationStrategy.RecommendationRule> rule = recommendationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningRequirement));

        Assertions.assertFalse(rule.isPresent());
    }
}
