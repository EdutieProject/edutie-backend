package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityType;
import jakarta.persistence.Entity;

@Entity
public class SimpleProblemActivity extends ActivityBase {
    private String introductionText;
    private String problemText;

    @Override
    public ActivityType getActivityType() {
        return ActivityType.REMEMBERING_ACTIVITY;
    }
}
