package com.edutie.infrastructure.persistence.implementation.learning;

import com.edutie.TestUtils;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningexperience.implementations.SimpleProblemActivityLearningExperience;
import com.edutie.domain.core.learning.learningexperience.persistence.LearningExperiencePersistence;
import com.edutie.infrastructure.persistence.implementation.learning.repositories.learningexperience.SimpleProblemActivityLearningExperienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LearningExperiencePersistenceImplementationTest {
    @Autowired
    private LearningExperiencePersistence learningExperiencePersistence;
    @Autowired
    private SimpleProblemActivityLearningExperienceRepository simpleProblemActivityLearningExperienceRepository;

    @Test
    void getById() throws Throwable {
        SimpleProblemActivityLearningExperience learningExperience = new SimpleProblemActivityLearningExperience();
        TestUtils.setPrivateField(learningExperience, "id", new LearningExperienceId());
        simpleProblemActivityLearningExperienceRepository.save(learningExperience);

        WrapperResult<LearningExperience<?>> wrapperResult = learningExperiencePersistence.getById(learningExperience.getId());

        assertTrue(wrapperResult.isSuccess());
        assertNotNull(wrapperResult.getValue());
    }

    @Test
    void save() throws Throwable {
        LearningExperience<?> learningExperience = new SimpleProblemActivityLearningExperience();
        TestUtils.setPrivateField(learningExperience, "id", new LearningExperienceId());
        System.out.println(learningExperience.getId());
        learningExperiencePersistence.save(learningExperience).throwIfFailure();

        assertTrue(simpleProblemActivityLearningExperienceRepository.findById(learningExperience.getId()).isPresent());
    }

    @Test
    void removeById() throws Throwable {
        SimpleProblemActivityLearningExperience learningExperience = new SimpleProblemActivityLearningExperience();
        TestUtils.setPrivateField(learningExperience, "id", new LearningExperienceId());
        simpleProblemActivityLearningExperienceRepository.save(learningExperience);

        assertTrue(simpleProblemActivityLearningExperienceRepository.findById(learningExperience.getId()).isPresent());

        learningExperiencePersistence.removeById(learningExperience.getId()).throwIfFailure();

        assertFalse(simpleProblemActivityLearningExperienceRepository.findById(learningExperience.getId()).isPresent());
    }

}