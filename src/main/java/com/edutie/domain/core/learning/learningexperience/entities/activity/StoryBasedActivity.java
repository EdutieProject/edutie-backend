package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityType;
import jakarta.persistence.Entity;

import java.net.URL;

@Entity
public class StoryBasedActivity extends ActivityBase {
    private String problemText;
    private URL imageURL;
    //TODO: add answers

    @Override
    public ActivityType getActivityType() {
        return ActivityType.UNDERSTANDING_ACTIVITY;
    }
}
