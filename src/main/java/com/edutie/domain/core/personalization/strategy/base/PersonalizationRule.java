package com.edutie.domain.core.personalization.strategy.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A base class for personalization rule. Personalization rule encompasses context for
 * the personalization technology to interpret and use in the personalization process
 */
@Getter
@AllArgsConstructor
public abstract class PersonalizationRule<T> {
    private T context;

    @JsonProperty("personalizationType")
    public String getPersonalizationTypeCode() {
        return this.getClass().getSimpleName().replace("Rule", "").toUpperCase();
    }
}
