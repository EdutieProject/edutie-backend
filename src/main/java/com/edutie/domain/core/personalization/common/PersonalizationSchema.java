package com.edutie.domain.core.personalization.common;

import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;

import java.util.Set;

/**
 * Personalization schema supertype used for providing the necessary data for personalization purposes.
 */
public interface PersonalizationSchema {
    Set<PersonalizationRule<?>> getPersonalizationRules();
}
