package com.edutie.domain.core.learning.learningexperience.implementations;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.StoryBasedActivity;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class StoryBasedActivityLearningExperience extends LearningExperience<StoryBasedActivity> {
}
