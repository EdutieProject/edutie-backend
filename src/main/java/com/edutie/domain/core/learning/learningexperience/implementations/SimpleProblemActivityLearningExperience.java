package com.edutie.domain.core.learning.learningexperience.implementations;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.SimpleProblemActivity;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class SimpleProblemActivityLearningExperience extends LearningExperience<SimpleProblemActivity> {
}
