package com.edutie.domain.core.personalization.learningexperience.activitytypechoice.base;

import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.domain.core.personalization.common.PersonalizationStrategy;

public interface LearningExperienceActivityTypeChoiceStrategy<TLearningExperienceActivityClassType extends Class<? extends Activity>, TRule extends PersonalizationRule<TLearningExperienceActivityClassType>>
        extends PersonalizationStrategy<TLearningExperienceActivityClassType, TRule> {
}
