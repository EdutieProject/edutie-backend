package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.OptimizationStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OptimizationStrategyTests {

    @Test
    public void abilityOptimizationStrategyTest()
    {
        OptimizationStrategy<Ability> abilityOptimizationStrategy = new OptimizationStrategy<>();
        abilityOptimizationStrategy.setOptimizationDescription(
                new PromptFragment("Sample prompt fragment")
        );
        abilityOptimizationStrategy.setRequiredValue(
                5.0
        );
        abilityOptimizationStrategy.setTrait(
                Ability.CRITICAL_THINKING
        );
        assertEquals(
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

}
