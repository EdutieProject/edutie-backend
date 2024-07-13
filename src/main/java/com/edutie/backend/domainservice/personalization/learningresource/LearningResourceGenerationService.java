package com.edutie.backend.domainservice.personalization.learningresource;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.student.Student;
import validation.WrapperResult;

public interface LearningResourceGenerationService {
    WrapperResult<LearningResource> generateLearningResource(LearningResourceDefinition lrd, Student student);
}
