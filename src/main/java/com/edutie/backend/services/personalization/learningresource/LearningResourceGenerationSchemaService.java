package com.edutie.backend.services.personalization.learningresource;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.student.Student;
import validation.WrapperResult;

public interface LearningResourceGenerationSchemaService {
    WrapperResult<LearningResource> generateLearningResource(LearningResourceDefinition lrd, Student student);
}
