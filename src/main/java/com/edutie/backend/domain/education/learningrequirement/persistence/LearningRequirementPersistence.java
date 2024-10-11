package com.edutie.backend.domain.education.learningrequirement.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import validation.WrapperResult;

import java.util.List;

public interface LearningRequirementPersistence extends Persistence<LearningRequirement, LearningRequirementId> {
}
