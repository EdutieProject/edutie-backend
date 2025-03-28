package com.edutie.mocks.persistence.learningexperience;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.mocks.learningexperience.SampleLearningExperience;
import com.edutie.mocks.persistence.learningexperience.base.MockLearningExperiencePersistenceBase;
import validation.WrapperResult;

public class CreateLearningResultLearningExperiencePersistence extends MockLearningExperiencePersistenceBase {
    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param learningExperienceId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<LearningExperience<?>> getById(LearningExperienceId learningExperienceId) {
        return WrapperResult.successWrapper(SampleLearningExperience.create());
    }
}
