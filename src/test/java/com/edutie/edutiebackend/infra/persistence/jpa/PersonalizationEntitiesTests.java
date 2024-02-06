package com.edutie.edutiebackend.infra.persistence.jpa;

import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories.AbilityOptimizationStrategyRepository;
import com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories.IntelligenceOptimizationStrategyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonalizationEntitiesTests {

    UserId testUser = new UserId();

    @Autowired
    IntelligenceOptimizationStrategyRepository intelligenceOptimizationStrategyRepository;
    @Autowired
    AbilityOptimizationStrategyRepository abilityOptimizationStrategyRepository;

    @Test
    public void intelligenceOptimizationStrategyCreateRetrieveTest() {
        IntelligenceOptimizationStrategy optimizationStrategy = new IntelligenceOptimizationStrategy();
        optimizationStrategy.setId(new OptimizationStrategyId());
        optimizationStrategy.setCreatedBy(testUser);
        optimizationStrategy.setOptimizationDescription(PromptFragment.of("Hello"));
        optimizationStrategy.setTrait(Intelligence.LOGICAL);
        intelligenceOptimizationStrategyRepository.saveAndFlush(optimizationStrategy);

        var fetched = intelligenceOptimizationStrategyRepository.findById(optimizationStrategy.getId());
        assertTrue(fetched.isPresent());
    }

    @Test
    public void abilityOptimizationStrategyCreateRetrieveTest() {
        AbilityOptimizationStrategy optimizationStrategy = new AbilityOptimizationStrategy();
        optimizationStrategy.setId(new OptimizationStrategyId());
        optimizationStrategy.setCreatedBy(testUser);
        optimizationStrategy.setTrait(Ability.CRITICAL_THINKING);
        abilityOptimizationStrategyRepository.saveAndFlush(optimizationStrategy);

        var fetched = abilityOptimizationStrategyRepository.findById(optimizationStrategy.getId());
        assertTrue(fetched.isPresent());
        assertEquals(Ability.CRITICAL_THINKING, fetched.get().getTrait());
    }
}
