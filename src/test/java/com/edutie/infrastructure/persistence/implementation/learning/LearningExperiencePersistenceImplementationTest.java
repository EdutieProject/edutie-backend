package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.SimpleProblemActivityLearningExperienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LearningExperiencePersistenceImplementationTest {
    @Autowired
    private LearningExperiencePersistence learningExperiencePersistence;
    @Autowired
    private SimpleProblemActivityLearningExperienceRepository simpleProblemActivityLearningExperienceRepository;

    @Test
    @Transactional
    void getById() {
        LearningExperience<?> learningExperience = SimpleProblemActivityLearningExperience.create();
        learningExperiencePersistence.save(learningExperience).throwIfFailure();

        WrapperResult<LearningExperience<?>> wrapperResult = learningExperiencePersistence.getById(learningExperience.getId());

        assertTrue(wrapperResult.isSuccess());
        assertNotNull(wrapperResult.getValue());
    }

    @Test
    void save() {
        LearningExperience<?> learningExperience = SimpleProblemActivityLearningExperience.create();
        System.out.println(learningExperience.getId());
        learningExperiencePersistence.save(learningExperience).throwIfFailure();

        assertTrue(simpleProblemActivityLearningExperienceRepository.findById(learningExperience.getId()).isPresent());
    }

    @Test
    void removeById() {
        SimpleProblemActivityLearningExperience learningExperience = SimpleProblemActivityLearningExperience.create();
        simpleProblemActivityLearningExperienceRepository.save(learningExperience);

        assertTrue(simpleProblemActivityLearningExperienceRepository.findById(learningExperience.getId()).isPresent());

        learningExperiencePersistence.removeById(learningExperience.getId()).throwIfFailure();

        assertFalse(simpleProblemActivityLearningExperienceRepository.findById(learningExperience.getId()).isPresent());
    }

}