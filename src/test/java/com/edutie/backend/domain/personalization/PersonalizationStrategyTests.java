package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.strategy.FeedbackRemediationStrategy;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class PersonalizationStrategyTests {
    @Autowired
    MockUser mockUser;
    @Autowired
    FeedbackRemediationStrategy feedbackRemediationStrategy;

    @Test
    public void feedbackRemediationStrategyQualifiesTest() throws Throwable {
        LearningRequirement learningRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
        Optional<FeedbackRemediationStrategy.FeedbackRemediationRule> rule = feedbackRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningRequirement));

        Assertions.assertTrue(rule.isPresent());
        Assertions.assertEquals(Feedback.of("Hello"), rule.get().getContext());
    }

    @Test
    public void feedbackRemediationStrategyNoQualificationTest() throws Throwable {
        LearningRequirement learningRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
        Optional<FeedbackRemediationStrategy.FeedbackRemediationRule> rule = feedbackRemediationStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningRequirement));

        Assertions.assertFalse(rule.isPresent());
    }
}
