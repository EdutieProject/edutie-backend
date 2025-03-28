package com.edutie.domain.core.education.learningsubject.persistence;

import com.edutie.domain.core.common.persistence.Persistence;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import validation.WrapperResult;

import java.util.List;

public interface LearningSubjectPersistence extends Persistence<LearningSubject, LearningSubjectId> {
    /**
     * Retrieves learning subjects created by educator
     *
     * @param educatorId educator id
     * @return Wrapper Result of Learning Subject List
     */
    WrapperResult<List<LearningSubject>> getCreatedLearningSubjects(Educator educatorId);

    /**
     * Retrieves learning subject that has an elemental requirement of provided id.
     *
     * @param elementalRequirementId elemental requirement id
     * @return Wrapper Result of Learning Subject
     */
    WrapperResult<LearningSubject> getLearningSubjectByElementalRequirementId(ElementalRequirementId elementalRequirementId);
}
