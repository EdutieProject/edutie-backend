package com.edutie.domain.core.personalization.learningexperience.contentadjustment.base;

import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.domain.core.personalization.common.PersonalizationStrategy;

public interface ContentAdjustmentStrategy<T, TPersonalizationRule extends PersonalizationRule<T>>
        extends PersonalizationStrategy<T, TPersonalizationRule> {
}
