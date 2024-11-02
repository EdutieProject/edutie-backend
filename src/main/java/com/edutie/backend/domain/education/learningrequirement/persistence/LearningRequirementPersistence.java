package com.edutie.backend.domain.education.learningrequirement.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import validation.WrapperResult;

import java.util.List;

public interface LearningRequirementPersistence extends Persistence<LearningRequirement, LearningRequirementId> {
    /**
     * Retrieves a number of learning requirements. If the returned list does not contain the specified number
     * of requirements, that means there is not enough of them in the persistence.
     * @param number number of l.reqs to retrieve
     * @return Wrapper result of learning requirement list
     */
    WrapperResult<List<LearningRequirement>> getAny(int number);
}
