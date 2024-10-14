package com.edutie.backend.domain.personalization.common;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.Getter;

import java.util.List;
import java.util.Set;

/**
 * Base class for personalization details.
 *
 * @param <T>
 */
@Getter
public abstract class PersonalizedDetails<T extends PersonalizationRule> {
    protected List<T> personalizationRules;

    /**
     * Used for creating personalization rules, usually in initialization of personalization schemas
     * @param student student for which personalization is done
     * @param learningResultPersistence student's learning result persistence
     * @param knowledgeCorrelations knowledge correlations for the personalization
     */
    protected abstract void createPersonalizationRules(Student student, LearningResultPersistence learningResultPersistence, Set<KnowledgeCorrelation> knowledgeCorrelations);
}
