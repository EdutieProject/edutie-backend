package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.common.LearningObjectiveType;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class OnlineDiscussionSimulationActivity extends ActivityBase {
    private String firstComment;
    private String secondComment;

    @Override
    public LearningObjectiveType getActivityType() {
        return LearningObjectiveType.ANALYZE;
    }
}
