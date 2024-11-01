package com.edutie.backend.domain.personalization.learningresourcedefinition.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import validation.WrapperResult;

import java.util.List;

public interface LearningResourceDefinitionPersistence extends Persistence<LearningResourceDefinition, LearningResourceDefinitionId> {

    WrapperResult<List<LearningResourceDefinition>> getByAuthorEducator(EducatorId educatorId);
}
