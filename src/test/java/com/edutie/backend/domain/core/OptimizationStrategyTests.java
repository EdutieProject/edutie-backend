package com.edutie.backend.domain.core;

import com.edutie.backend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.core.common.studenttraits.Ability;
import com.edutie.backend.domain.core.common.studenttraits.Intelligence;
import com.edutie.backend.domain.core.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.base.OptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OptimizationStrategyTests {

    @Test
    public void abilityOptimizationStrategyTest()
    {
        OptimizationStrategy<Ability> abilityOptimizationStrategy = new AbilityOptimizationStrategy();
        abilityOptimizationStrategy.setOptimizationDescription(
                new PromptFragment("Sample prompt fragment")
        );
        abilityOptimizationStrategy.setRequiredValue(
                5.0
        );
        abilityOptimizationStrategy.setTrait(
                Ability.CRITICAL_THINKING
        );
        Assertions.assertEquals(
                abilityOptimizationStrategy.getOptimizationDescription(),
                new PromptFragment("Sample prompt fragment")
        );
        assertEquals(
                abilityOptimizationStrategy.getRequiredValue(),
                5.0
        );
        assertEquals(
                abilityOptimizationStrategy.getTrait(),
                Ability.CRITICAL_THINKING
        );
    }

    @Test
    public void intelligenceOptimizationStrategyTest()
    {
        OptimizationStrategy<Intelligence> intelligenceOptimizationStrategy = new IntelligenceOptimizationStrategy();
        intelligenceOptimizationStrategy.setTrait(Intelligence.LOGICAL);
        intelligenceOptimizationStrategy.setOptimizationDescription(PromptFragment.of("Sample fragment"));
        intelligenceOptimizationStrategy.setRequiredValue(1.0);
        intelligenceOptimizationStrategy.setId(new OptimizationStrategyId());

        assertNotNull(intelligenceOptimizationStrategy.getId());
        Assertions.assertEquals(intelligenceOptimizationStrategy.getOptimizationDescription().text(), "Sample fragment");
        assertEquals(intelligenceOptimizationStrategy.getTrait(), Intelligence.LOGICAL);
    }
}
