package com.edutie.backend.domain.personalization.common;

import java.util.Set;

/**
 * Personalization schema supertype used for providing the necessary data for personalization purposes.
 */
public interface PersonalizationSchema {
    Set<PersonalizationRule> getPersonalizationRules();
}
