package com.edutie.backend.domain.education.learningrequirement.persistence;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;

import java.util.List;

public interface LearningRequirementPersistence extends PersistenceBase<LearningRequirement, LearningRequirementId> {
    /**
     * Retrieve all learning requirements associated with given creator
     *
     * @param educatorId creator id
     * @return Learning Requirement list
     */
    List<LearningRequirement> getAllOfCreatorId(EducatorId educatorId);

    /**
     * Retrieve all learning requirements associated with given science
     *
     * @param scienceId science id
     * @return Learning Requirement list
     */
    List<LearningRequirement> getAllOfScienceId(ScienceId scienceId);
}
