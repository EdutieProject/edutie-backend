package com.edutie.mocks.learningexperience;

import com.edutie.domain.core.learning.common.LearningObjectiveType;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;

public class SampleActivity implements Activity {
    @Override
    public LearningObjectiveType getActivityType() {
        return LearningObjectiveType.REMEMBER;
    }
}
