package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.implementations.SimpleProblemActivityLearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningresult.SimpleProblemActivityLearningResultRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LearningResultPersistenceImplementationTest {
    @Autowired
    private LearningResultPersistence learningResultPersistence;
    @Autowired
    private SimpleProblemActivityLearningResultRepository simpleProblemActivityLearningResultRepository;


    @Test
    void getById() {
        SimpleProblemActivityLearningResult learningResult = SimpleProblemActivityLearningResult.create();
        simpleProblemActivityLearningResultRepository.save(learningResult);

        WrapperResult<LearningResult<?>> wrapperResult = learningResultPersistence.getById(learningResult.getId());

        assertTrue(wrapperResult.isSuccess());
        assertNotNull(wrapperResult.getValue());
    }

    @Test
    void save() {
        LearningResult<?> learningExperience = SimpleProblemActivityLearningResult.create();
        System.out.println(learningExperience.getId());
        learningResultPersistence.save(learningExperience).throwIfFailure();

        assertTrue(simpleProblemActivityLearningResultRepository.findById(learningExperience.getId()).isPresent());
    }

    @Test
    void removeById() {
        SimpleProblemActivityLearningResult learningExperience = SimpleProblemActivityLearningResult.create();
        simpleProblemActivityLearningResultRepository.save(learningExperience);

        assertTrue(simpleProblemActivityLearningResultRepository.findById(learningExperience.getId()).isPresent());

        learningResultPersistence.removeById(learningExperience.getId()).throwIfFailure();

        assertFalse(simpleProblemActivityLearningResultRepository.findById(learningExperience.getId()).isPresent());
    }

}