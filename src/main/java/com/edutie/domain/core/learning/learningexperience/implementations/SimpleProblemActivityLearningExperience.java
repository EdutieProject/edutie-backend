package com.edutie.domain.core.learning.learningexperience.implementations;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.SimpleProblemActivity;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class SimpleProblemActivityLearningExperience extends LearningExperience<SimpleProblemActivity> {

    public static SimpleProblemActivityLearningExperience create() {
        SimpleProblemActivityLearningExperience learningExperience = new SimpleProblemActivityLearningExperience();
        learningExperience.setId(new LearningExperienceId());
        return learningExperience;
    }
}
