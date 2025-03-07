package com.edutie.domain.core.personalization.strategy;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubjectReference;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * A personalization strategy for recommending additional learning requirements.
 */
@Component
@RequiredArgsConstructor
public class RecommendationStrategy implements PersonalizationStrategy<KnowledgeSubjectReference, RecommendationStrategy.RecommendationRule> {
    private final LearningResultPersistence learningResultPersistence;

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student          student
     * @param learningSubjects learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<RecommendationRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        return Optional.empty();
    }

    public static class RecommendationRule extends PersonalizationRule<KnowledgeSubjectReference> {
        public RecommendationRule(KnowledgeSubjectReference context) {
            super(context);
        }
    }
}


