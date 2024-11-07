package com.edutie.backend.domain.personalization.common;

import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;

import java.util.Set;

/**
 * Personalization schema supertype used for providing the necessary data for personalization purposes.
 */
public interface PersonalizationSchema {
    Set<PersonalizationRule<?>> getPersonalizationRules();
}
