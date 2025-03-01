package com.edutie.domain.core.learning.learningresult.implementations;

import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.submission.SimpleProblemActivitySolutionSubmission;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class StoryBasedActivityLearningResult extends LearningResult<SimpleProblemActivitySolutionSubmission> {
}
