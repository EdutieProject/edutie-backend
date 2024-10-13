package com.edutie.backend.domain.personalization.learningresourcegenerationschema.details.base;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.rules.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.Getter;

import java.util.List;

/**
 * Base class for personalized details of learning resource contents
 * @param <T>
 */
@Getter
public abstract class PersonalizedDetails<T extends PersonalizationRule> {
    protected List<T> personalizationRules;

    protected void createPersonalizationRules(List<KnowledgeCorrelation> knowledgeCorrelations, Student student) {
        //TODO
    }
}
