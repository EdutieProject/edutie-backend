package com.edutie.mocks.learningexperience;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.LearningExperienceRequirement;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;

import java.util.UUID;

public class SampleLearningExperience extends LearningExperience<SampleActivity> {
    public static SampleLearningExperience create() {
        SampleLearningExperience learningExperience = new SampleLearningExperience();
        learningExperience.setId(new LearningExperienceId());
        learningExperience.getRequirements().add(LearningExperienceRequirement.from(new ElementalRequirement() {
            @Override
            public ElementalRequirementId getId() {
                return new ElementalRequirementId(UUID.fromString("3a074255-9e99-434a-8d80-a05d0a20784b"));
            }
        }));
        return learningExperience;
    }
}
