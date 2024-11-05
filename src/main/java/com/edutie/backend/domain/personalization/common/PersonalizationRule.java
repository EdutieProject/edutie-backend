package com.edutie.backend.domain.personalization.common;

import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import lombok.Getter;

/**
 * A base class for personalization rule. Personalization rule encompasses
 * related past assessments that could be used for personalization.
 */
@Getter
public class PersonalizationRule {
    private Feedback pastFeedback;
    private PersonalizationRuleType type;

    public static PersonalizationRule fromAssessment(Assessment assessment) {
        PersonalizationRule rule = new PersonalizationRule();
        rule.pastFeedback = assessment.getFeedback();
        rule.type = PersonalizationRuleType.REINFORCEMENT; // TODO: compute value
        return rule;
    }
}
