package com.edutie.domain.personalization;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.personalization.strategy.RecommendationStrategy;
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
public class RecommendationStrategyTests {
    @Autowired
    MockUser mockUser;
    RecommendationStrategy recommendationStrategy;
    LearningSubject learningSubject;
    @Autowired
    KnowledgeMapService knowledgeMapService;

    @BeforeEach
    public void testSetup() {
        learningSubject = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
    }

    @Test
    public void recommendationStrategyQualifiesTest() {
        recommendationStrategy = new RecommendationStrategy(
                knowledgeMapService,
                LearningHistoryMocker.learningResultPersistenceForRecommendationStrategy(mockUser.getStudentProfile(), learningSubject, Grade.of(5))
        );

        Optional<RecommendationStrategy.RecommendationRule> rule = recommendationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningSubject));

        Assertions.assertTrue(rule.isPresent());
    }

    @Test
    public void recommendationStrategyNoQualificationTest() {
        recommendationStrategy = new RecommendationStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForRecommendationStrategy(mockUser.getStudentProfile(), learningSubject, new Grade(2))
        );

        Optional<RecommendationStrategy.RecommendationRule> rule = recommendationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningSubject));

        Assertions.assertFalse(rule.isPresent());
    }
}
