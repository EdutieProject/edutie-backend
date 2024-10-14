package com.edutie.backend.domain.personalization.common;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.Getter;

import java.util.List;
import java.util.Set;

/**
 * Base class for personalized details of learning resource contents
 *
 * @param <T>
 */
@Getter
public abstract class PersonalizedDetails<T extends PersonalizationRule> {
    protected List<T> personalizationRules;

    protected abstract void createPersonalizationRules(Student student, LearningResultPersistence learningResultPersistence, Set<KnowledgeCorrelation> knowledgeCorrelations);
}
