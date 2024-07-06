package com.edutie.backend.infrastructure.persistence.implementation.persistence.education;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ScienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.Result;

@SpringBootTest
public class LearningRequirementPersistenceTests {
    @Autowired
    LearningRequirementPersistence learningRequirementPersistence;
    @Autowired
    EducatorRepository educatorRepository;
    @Autowired
    ScienceRepository scienceRepository;
    private final UserId userId = new UserId();
    private final Administrator administrator = Administrator.create(userId);
    private final Educator educator = Educator.create(userId, administrator);
    private final Science science = Science.create(educator).getValue();

    @BeforeEach
    public void testSetup() {
        educatorRepository.save(educator);
        scienceRepository.save(science);
    }

    @Test
    public void defaultSaveTest() {
        LearningRequirement learningRequirement = LearningRequirement.create(educator, science);
        assert learningRequirementPersistence.save(learningRequirement).isSuccess();

        LearningRequirement fetched = learningRequirementPersistence.getById(learningRequirement.getId()).getValue();
        assert fetched.equals(learningRequirement);
    }

    @Test
    public void wholeSaveTest() {
        LearningRequirement learningRequirement = LearningRequirement.create(educator, science);
        learningRequirement.appendSubRequirement("hello", "world!");
        learningRequirement.appendSubRequirement("hello", "universe!");
        Result result = learningRequirementPersistence.save(learningRequirement);
        if (result.isFailure())
            throw new AssertionError(result.getError());

        LearningRequirement fetched = learningRequirementPersistence.getById(learningRequirement.getId()).getValue();
        assert fetched.equals(learningRequirement);
        assert fetched.getSubRequirements().size() == 2;
    }
}
