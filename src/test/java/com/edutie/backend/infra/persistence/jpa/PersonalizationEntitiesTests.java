package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.core.common.identities.UserId;
import com.edutie.backend.domain.core.common.studenttraits.Ability;
import com.edutie.backend.domain.core.common.studenttraits.Intelligence;
import com.edutie.backend.domain.core.learningresource.LearningResource;
import com.edutie.backend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.core.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.IntelligenceOptimizationStrategyRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.AbilityOptimizationStrategyRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LearningResourceRepository;
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

    @Autowired
    LearningResourceRepository learningResourceRepository;

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

    @Test
    public void learningResourceCreateRetrieveTest() {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(testUser);

        learningResourceRepository.save(learningResource);

        var fetched = learningResourceRepository.findById(learningResource.getId());
        assertTrue(fetched.isPresent());
        assertEquals(learningResource.getId(), fetched.get().getId());
    }

    @Test
    public void learningResourceOptimizationTest() {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(testUser);

        IntelligenceOptimizationStrategy ios = new IntelligenceOptimizationStrategy();
        ios.setId(new OptimizationStrategyId());
        ios.setCreatedBy(testUser);
        AbilityOptimizationStrategy aos = new AbilityOptimizationStrategy();
        aos.setId(new OptimizationStrategyId());
        aos.setCreatedBy(testUser);
        intelligenceOptimizationStrategyRepository.save(ios);
        abilityOptimizationStrategyRepository.save(aos);

        learningResource.addOptimizationStrategy(aos, AbilityOptimizationStrategy.class);
        learningResource.addOptimizationStrategy(ios, IntelligenceOptimizationStrategy.class);
        learningResourceRepository.save(learningResource);

        var fetched = learningResourceRepository.findById(learningResource.getId());
        assertTrue(fetched.isPresent());
        assertEquals(2, learningResource.getAllOptimizationStrategies().size());
    }
}
