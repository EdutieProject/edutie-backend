package com.edutie.backend.infrastucture.persistence.implementation.persistence.personalization;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LearningResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LearningResultPersistenceImplementation implements LearningResultPersistence {
    private final LearningResultRepository learningResultRepository;
    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<LearningResult, LearningResultId> getRepository() {
        return learningResultRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<LearningResult> entityClass() {
        return LearningResult.class;
    }

}
