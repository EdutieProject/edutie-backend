package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationStrategy;
import com.edutie.backend.domain.personalization.rule.selectionengine.PersonalizationRuleSelectionEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class MockPersonalizationRule extends PersonalizationRule<Object> {
    public MockPersonalizationRule(Object context) {
        super(context);
    }
}

class AnotherMockPersonalizationRule extends PersonalizationRule<Object> {
    public AnotherMockPersonalizationRule(Object context) {
        super(context);
    }
}

class ThirdMockPersonalizationRule extends PersonalizationRule<Object> {
    public ThirdMockPersonalizationRule(Object context) {
        super(context);
    }
}

@SpringBootTest
public class PersonalizationRuleSelectionEngineTests {
    PersonalizationRuleSelectionEngine ruleSelectionEngine = new PersonalizationRuleSelectionEngine(List.of(
            new PersonalizationStrategy<Object, MockPersonalizationRule>() {
                @Override
                public Optional<MockPersonalizationRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
                    return Optional.of(new MockPersonalizationRule(learningRequirements));
                }

                @Override
                public int getPriority() {
                    return 3;
                }
            },
            new PersonalizationStrategy<Object, AnotherMockPersonalizationRule>() {
                @Override
                public Optional<AnotherMockPersonalizationRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
                    return Optional.of(new AnotherMockPersonalizationRule(learningRequirements));
                }

                @Override
                public int getPriority() {
                    return 1;
                }
            },
            new PersonalizationStrategy<Object, ThirdMockPersonalizationRule>() {
                @Override
                public Optional<ThirdMockPersonalizationRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
                    return Optional.of(new ThirdMockPersonalizationRule(learningRequirements));
                }

                @Override
                public int getPriority() {
                    return 2;
                }
            }
    ));

    @Test
    public void ruleSelectionTest() {
        Set<PersonalizationRule<?>> personalizationRules = ruleSelectionEngine.chooseRulesByRequirementsAndHistory(Set.of(), List.of());

        System.out.println(personalizationRules.stream().map(o -> o.getClass().getSimpleName() + ", ").collect(Collectors.joining()));

        Assertions.assertFalse(personalizationRules.isEmpty());
        Assertions.assertEquals(2, personalizationRules.size());
        Assertions.assertFalse(personalizationRules.stream().anyMatch(r -> r instanceof AnotherMockPersonalizationRule)); // lowest priority rule
    }
}
