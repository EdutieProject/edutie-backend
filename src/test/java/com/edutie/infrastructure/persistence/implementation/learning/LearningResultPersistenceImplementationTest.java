package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.TestUtils;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
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
    void getById() throws Throwable {
        SimpleProblemActivityLearningResult learningResult = new SimpleProblemActivityLearningResult();
        TestUtils.setPrivateField(learningResult, "id", new LearningResultId());
        simpleProblemActivityLearningResultRepository.save(learningResult);

        WrapperResult<LearningResult<?>> wrapperResult = learningResultPersistence.getById(learningResult.getId());

        assertTrue(wrapperResult.isSuccess());
        assertNotNull(wrapperResult.getValue());
    }

    @Test
    void save() throws Throwable {
        LearningResult<?> learningResult = new SimpleProblemActivityLearningResult();
        TestUtils.setPrivateField(learningResult, "id", new LearningResultId());
        System.out.println(learningResult.getId());
        learningResultPersistence.save(learningResult).throwIfFailure();

        assertTrue(simpleProblemActivityLearningResultRepository.findById(learningResult.getId()).isPresent());
    }

    @Test
    void removeById() throws Throwable {
        SimpleProblemActivityLearningResult learningResult = new SimpleProblemActivityLearningResult();
        TestUtils.setPrivateField(learningResult, "id", new LearningResultId());
        simpleProblemActivityLearningResultRepository.save(learningResult);

        assertTrue(simpleProblemActivityLearningResultRepository.findById(learningResult.getId()).isPresent());

        learningResultPersistence.removeById(learningResult.getId()).throwIfFailure();

        assertFalse(simpleProblemActivityLearningResultRepository.findById(learningResult.getId()).isPresent());
    }

}