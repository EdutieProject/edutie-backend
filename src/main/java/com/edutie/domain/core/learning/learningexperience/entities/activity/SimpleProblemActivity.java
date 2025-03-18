package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.common.LearningObjectiveType;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class SimpleProblemActivity extends ActivityBase {
    private String introductionText;
    private String problemText;

    @Override
    public LearningObjectiveType getActivityType() {
        return LearningObjectiveType.REMEMBER;
    }

    public static SimpleProblemActivity create(
            String introductionText, String problemText
    ) {
        SimpleProblemActivity simpleProblemActivity = new SimpleProblemActivity();
        simpleProblemActivity.introductionText = introductionText;
        simpleProblemActivity.problemText = problemText;
        return simpleProblemActivity;
    }
}
