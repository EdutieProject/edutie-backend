package com.edutie.backend.domainservice.personalization.learningresource;

import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.student.Student;
import validation.WrapperResult;

/**
 * This Service does create an L.R.G.S. using knowledge map.
 */
public interface LearningResourceGenerationSchemaService {
	WrapperResult<LearningResourceGenerationSchema> createSchema(LearningResourceDefinition lrd, Student student);
}
