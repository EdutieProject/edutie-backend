package com.edutie.backend.domain.personalization.common;

public enum PersonalizationType {
    /** For the refreshing of the distantly acquired knowledge */
    REFRESH,
    /** For reinforcement of the knowledge that was once known, but could be forgotten */
    REINFORCEMENT,
    /** For targeting the weak understanding of a concept */
    REMEDIATION,
    /** Recommending the correlated but not fully discovered subject */
    RECOMMENDATION;
}
