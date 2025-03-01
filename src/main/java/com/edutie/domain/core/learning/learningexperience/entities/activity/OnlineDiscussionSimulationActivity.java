package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityType;
import jakarta.persistence.Entity;

@Entity
public class OnlineDiscussionSimulationActivity extends ActivityBase {
    private String firstComment;
    private String secondComment;

    @Override
    public ActivityType getActivityType() {
        return ActivityType.ANALYZING_ACTIVITY;
    }
}
