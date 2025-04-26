package com.edutie.mocks.persistence.learningsubject.base;

import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public class MockLearningSubjectPersistenceBase implements LearningSubjectPersistence {
    /**
     * Retrieves learning subjects created by educator
     *
     * @param educatorId educator id
     * @return Wrapper Result of Learning Subject List
     */
    @Override
    public WrapperResult<List<LearningSubject>> getCreatedLearningSubjects(Educator educatorId) {
        return null;
    }

    /**
     * Retrieves learning subject that has an elemental requirement of provided id.
     *
     * @param elementalRequirementId elemental requirement id
     * @return Wrapper Result of Learning Subject
     */
    @Override
    public WrapperResult<LearningSubject> getLearningSubjectByElementalRequirementId(ElementalRequirementId elementalRequirementId) {
        return null;
    }

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param learningSubjectId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<LearningSubject> getById(LearningSubjectId learningSubjectId) {
        return null;
    }

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @param entity
     * @return Result object
     */
    @Override
    public Result save(LearningSubject entity) {
        return null;
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param learningSubjectId entity id
     * @return Result object
     */
    @Override
    public Result removeById(LearningSubjectId learningSubjectId) {
        return null;
    }

    /**
     * Removes the following entity from the database. This is a default method, which when not
     * override is correlated with removeById() method.
     *
     * @param entity entity
     * @return Result
     */
    @Override
    public Result remove(LearningSubject entity) {
        return null;
    }
}
