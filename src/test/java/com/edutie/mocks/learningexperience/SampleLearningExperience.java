package com.edutie.mocks.learningexperience;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;

public class SampleLearningExperience extends LearningExperience<SampleActivity> {
    public static SampleLearningExperience create() {
        SampleLearningExperience learningExperience = new SampleLearningExperience();
        learningExperience.setId(new LearningExperienceId());
        return learningExperience;
    }
}
