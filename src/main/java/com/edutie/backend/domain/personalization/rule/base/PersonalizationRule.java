package com.edutie.backend.domain.personalization.rule.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * A base class for personalization rule. Personalization rule encompasses
 * related past assessments that could be used for personalization.
 */
@Getter
public abstract class PersonalizationRule<T> {
    private T context;

    @JsonProperty("personalizationType")
    public String getPersonalizationTypeCode() {
        return this.getClass().getSimpleName().replace("PersonalizationRule", "");
    }
}
