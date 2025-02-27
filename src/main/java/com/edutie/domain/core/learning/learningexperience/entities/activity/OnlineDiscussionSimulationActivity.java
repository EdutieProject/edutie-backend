package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.common.Activity;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityType;

public class OnlineDiscussionSimulationActivity extends ActivityBase implements Activity {
    @Override
    public ActivityType getActivityType() {
        return ActivityType.ANALYZING_ACTIVITY;
    }
}
