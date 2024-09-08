package com.edutie.backend.domain.education.learningrequirement.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import validation.WrapperResult;

import java.util.List;

public interface LearningRequirementPersistence extends Persistence<LearningRequirement, LearningRequirementId> {
	/**
	 * Retrieve all learning requirements associated with given creator
	 *
	 * @param educatorId educator id
	 * @return Wrapper result of desired list
	 */
	WrapperResult<List<LearningRequirement>> getAllOfEducatorId(EducatorId educatorId);

	/**
	 * Retrieve all learning requirements associated with given science
	 *
	 * @param scienceId science id
	 * @return Wrapper result of desired list
	 */
	WrapperResult<List<LearningRequirement>> getAllOfScienceId(ScienceId scienceId);
}
