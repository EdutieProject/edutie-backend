package com.edutie.infrastructure.persistence.implementation.education;

import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.infrastructure.persistence.PersistenceError;
import com.edutie.infrastructure.persistence.base.DefaultPersistence;
import com.edutie.infrastructure.persistence.implementation.education.repositories.LearningSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LearningSubjectPersistenceImplementation
        extends DefaultPersistence<LearningSubject, LearningSubjectId> implements LearningSubjectPersistence {
    private final LearningSubjectRepository learningSubjectRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<LearningSubject, LearningSubjectId> getRepository() {
        return learningSubjectRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<LearningSubject> entityClass() {
        return LearningSubject.class;
    }

    @Override
    public WrapperResult<List<LearningSubject>> getCreatedLearningSubjects(Educator educator) {
        try {
            return Result.successWrapper(learningSubjectRepository.findAllByAuthorEducator(educator));
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
        }
    }
}
