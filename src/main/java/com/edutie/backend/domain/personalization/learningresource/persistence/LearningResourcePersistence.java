package com.edutie.backend.domain.personalization.learningresource.persistence;

import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.WrapperResult;

import java.util.List;

public interface LearningResourcePersistence extends Persistence<LearningResource, LearningResourceId> {

    /**
     * Retrieve all Learning Resources associated with given definition
     * @param learningResourceDefinitionId definition id
     * @return Wrapper result of desired list
     */
    WrapperResult<List<LearningResource>> getByLearningResourceDefinitionId(LearningResourceDefinitionId learningResourceDefinitionId);
}
