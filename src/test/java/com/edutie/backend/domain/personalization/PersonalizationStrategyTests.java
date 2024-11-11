package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.strategy.FeedbackRemediationStrategy;
import com.edutie.backend.mocks.EducationMocks;
import com.edutie.backend.mocks.LearningResultMocker;
import com.edutie.backend.mocks.MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
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
                Set.of(learningRequirement),
                List.of(
                        LearningResultMocker.createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(mockUser.getStudentProfile(), null, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(learningRequirement.getId(), new Grade(2), Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(1)
                        ),
                        LearningResultMocker.createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(mockUser.getStudentProfile(), null, "", 0),
                                Feedback.of("World"),
                                Set.of(Assessment.create(learningRequirement.getId(), new Grade(2), Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(2)
                        ))
        );

        Assertions.assertTrue(rule.isPresent());
        Assertions.assertEquals(Feedback.of("Hello"), rule.get().getContext());
    }

    @Test
    public void feedbackRemediationStrategyNoQualificationTest() throws Throwable {
        LearningRequirement learningRequirement = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
        Optional<FeedbackRemediationStrategy.FeedbackRemediationRule> rule = feedbackRemediationStrategy.qualifyRule(
                Set.of(learningRequirement),
                List.of(
                        LearningResultMocker.createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(mockUser.getStudentProfile(), null, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(learningRequirement.getId(), new Grade(4), Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(1)
                        ),
                        LearningResultMocker.createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(mockUser.getStudentProfile(), null, "", 0),
                                Feedback.of("World"),
                                Set.of(Assessment.create(learningRequirement.getId(), new Grade(4), Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(2)
                        ))
        );

        Assertions.assertFalse(rule.isPresent());
    }
}
