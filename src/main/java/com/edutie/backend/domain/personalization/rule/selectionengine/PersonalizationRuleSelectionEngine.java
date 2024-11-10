package com.edutie.backend.domain.personalization.rule.selectionengine;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.rule.RecommendationPersonalizationStrategy;
import com.edutie.backend.domain.personalization.rule.RefreshPersonalizationStrategy;
import com.edutie.backend.domain.personalization.rule.ReinforcementPersonalizationStrategy;
import com.edutie.backend.domain.personalization.rule.RemediationPersonalizationStrategy;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class PersonalizationRuleSelectionEngine {
    private final RecommendationPersonalizationStrategy recommendationPersonalizationStrategy;
    private final RemediationPersonalizationStrategy remediationPersonalizationStrategy;
    private final ReinforcementPersonalizationStrategy reinforcementPersonalizationStrategy;
    private final RefreshPersonalizationStrategy refreshPersonalizationStrategy;


    private List<PersonalizationStrategy<?, ? extends PersonalizationRule<?>>> getPersonalizationStrategies() {
        return List.of(
                recommendationPersonalizationStrategy,
                remediationPersonalizationStrategy,
                reinforcementPersonalizationStrategy,
                refreshPersonalizationStrategy
        );
    }

    public Set<PersonalizationRule<?>> chooseRulesByRequirementsAndHistory(
            Set<LearningRequirement> learningRequirements,
            List<LearningResult> pastResults
    ) {
        Set<PersonalizationRule<?>> rules = new HashSet<>();
        for (PersonalizationStrategy<?, ? extends PersonalizationRule<?>> strategy : getPersonalizationStrategies()) {
            strategy.qualifyRule(learningRequirements, pastResults).ifPresent(rules::add);
            if (rules.size() == 2)
                break;
        }
        return rules;
    }
}
