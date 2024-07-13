package com.edutie.backend.infrastucture.persistence.implementation.persistence.personalization;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LearningResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LearningResourcePersistenceImplementation implements LearningResourcePersistence {
    private final LearningResourceRepository learningResourceRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<LearningResource, LearningResourceId> getRepository() {
        return learningResourceRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<LearningResource> entityClass() {
        return LearningResource.class;
    }

    /**
     * Retrieve all Learning Resources associated with given student
     *
     * @param studentId student id
     * @return Wrapper result of desired list
     */
    @Override
    public WrapperResult<List<LearningResource>> getAllOfStudentId(StudentId studentId) {
        return WrapperResult.successWrapper(List.of());
    }
}
