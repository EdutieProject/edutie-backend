package com.edutie.infrastructure.persistence.implementation.education;

import com.edutie.TestUtils;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRepository;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRequirementRepository;
import com.edutie.mocks.MockUser;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LearningSubjectPersistenceImplementationTest {
    @Autowired
    private LearningSubjectPersistence learningSubjectPersistence;
    @Autowired
    private LearningSubjectRepository learningSubjectRepository;
    @Autowired
    private LearningSubjectRequirementRepository learningSubjectRequirementRepository;


    @Test
    void getById() throws Throwable {
        LearningSubject learningSubject = new LearningSubject();
        TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        learningSubjectRepository.save(learningSubject);

        WrapperResult<LearningSubject> learningSubjectWrapperResult = learningSubjectPersistence.getById(learningSubject.getId()).throwIfFailure();

        assertTrue(learningSubjectWrapperResult.isSuccess());
        assertNotNull(learningSubjectWrapperResult.getValue());
    }

    @Test
    void save() throws Throwable {
        LearningSubject learningSubject = new LearningSubject();
        TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        learningSubjectPersistence.save(learningSubject).throwIfFailure();

        Optional<LearningSubject> optionalLearningSubject = learningSubjectRepository.findById(learningSubject.getId());
        assertTrue(optionalLearningSubject.isPresent());
    }

    @Test
    void saveWithKnowledgeOrigin() throws Throwable {
        LearningSubject learningSubject = new LearningSubject();
        TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        KnowledgeOrigin knowledgeOrigin = new KnowledgeOrigin();
        knowledgeOrigin.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningSubject.setKnowledgeOrigin(knowledgeOrigin);
        learningSubjectPersistence.save(learningSubject).throwIfFailure();

        Optional<LearningSubject> optionalLearningSubject = learningSubjectRepository.findById(learningSubject.getId());
        assertTrue(optionalLearningSubject.isPresent());
        assertNotNull(optionalLearningSubject.get().getKnowledgeOrigin());
    }

    @Test
    void removeById() throws Throwable {
        LearningSubject learningSubject = new LearningSubject();
        TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        learningSubjectRepository.save(learningSubject);

        learningSubjectPersistence.removeById(learningSubject.getId()).throwIfFailure();

        assertFalse(learningSubjectRepository.findById(learningSubject.getId()).isPresent());
    }

}