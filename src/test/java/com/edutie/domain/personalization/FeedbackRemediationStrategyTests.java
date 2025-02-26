package com.edutie.domain.personalization;

import com.edutie.domain.core.education.learningrequirement.LearningSubject;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.personalization.strategy.FeedbackRemediationStrategy;
import com.edutie.mocks.EducationMocks;
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
public class FeedbackRemediationStrategyTests {
    @Autowired
    MockUser mockUser;
    FeedbackRemediationStrategy feedbackRemediationStrategy;
    LearningSubject learningSubject;

    @BeforeEach
    public void testSetup() {
        learningSubject = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
    }


    @Test
    public void feedbackRemediationStrategyQualifiesTest() {
        feedbackRemediationStrategy = new FeedbackRemediationStrategy(
                LearningHistoryMocker.learningResultPersistenceForFeedbackRemediationStrategy(mockUser.getStudentProfile(), learningSubject, new Grade(2))
        );

        Optional<FeedbackRemediationStrategy.FeedbackRemediationRule> rule = feedbackRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningSubject));

        Assertions.assertTrue(rule.isPresent());
        Assertions.assertEquals(Feedback.of("You need to improve"), rule.get().getContext());
    }

    @Test
    public void feedbackRemediationStrategyNoQualificationTest() {
        feedbackRemediationStrategy = new FeedbackRemediationStrategy(
                LearningHistoryMocker.learningResultPersistenceForFeedbackRemediationStrategy(mockUser.getStudentProfile(), learningSubject, new Grade(4))
        );

        Optional<FeedbackRemediationStrategy.FeedbackRemediationRule> rule = feedbackRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningSubject));

        Assertions.assertFalse(rule.isPresent());
    }
}
