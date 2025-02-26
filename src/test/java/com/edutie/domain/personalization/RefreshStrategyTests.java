package com.edutie.domain.personalization;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.personalization.strategy.RefreshStrategy;
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
public class RefreshStrategyTests {
    @Autowired
    MockUser mockUser;
    RefreshStrategy refreshStrategy;
    LearningSubject learningSubject;

    @BeforeEach
    public void testSetup() {
        learningSubject = EducationMocks.relatedLearningRequirement(mockUser.getEducatorProfile());
    }
    // TODO pomysl: immediate reinforcement strategy - po zmianie LReq powróć na chwilę do starego?

    @Test
    public void refreshStrategyQualifiesTest() {
        refreshStrategy = new RefreshStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForRefreshStrategy(mockUser.getStudentProfile(), learningSubject, new Grade(4))
        );

        Optional<RefreshStrategy.RefreshRule> rule = refreshStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningSubject));

        Assertions.assertTrue(rule.isPresent());
    }

    @Test
    public void refreshStrategyNoQualificationTest() {
        refreshStrategy = new RefreshStrategy(
                ExternalServiceMocks.knowledgeMapServiceMock(),
                LearningHistoryMocker.learningResultPersistenceForRefreshStrategy(mockUser.getStudentProfile(), learningSubject, new Grade(1))
        );

        Optional<RefreshStrategy.RefreshRule> rule = refreshStrategy.qualifyRule(
                mockUser.getStudentProfile(), Set.of(learningSubject));

        Assertions.assertFalse(rule.isPresent());
    }
}
