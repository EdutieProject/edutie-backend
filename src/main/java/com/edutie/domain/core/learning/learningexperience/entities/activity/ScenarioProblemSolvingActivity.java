package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.common.LearningObjectiveType;
import jakarta.persistence.Entity;

import java.net.URL;

@Entity
public class ScenarioProblemSolvingActivity extends ActivityBase {
    private String problemText;
    private URL imageURL;

    @Override
    public LearningObjectiveType getActivityType() {
        return LearningObjectiveType.APPLY;
    }
}
