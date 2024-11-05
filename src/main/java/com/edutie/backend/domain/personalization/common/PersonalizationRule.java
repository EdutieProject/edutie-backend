package com.edutie.backend.domain.personalization.common;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import lombok.Getter;

import java.util.List;

/**
 * A base class for personalization rule. Personalization rule encompasses
 * related past assessments that could be used for personalization.
 */
@Getter
public class PersonalizationRule {
    private Feedback pastFeedback;
    private PersonalizationRuleType type;
}
