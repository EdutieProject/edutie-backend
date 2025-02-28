package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityType;

public class OnlineDiscussionSimulationActivity extends ActivityBase {
    @Override
    public ActivityType getActivityType() {
        return ActivityType.ANALYZING_ACTIVITY;
    }
}
