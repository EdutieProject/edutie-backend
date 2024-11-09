package com.edutie.backend.domain.personalization.rule.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Locale;

/**
 * A base class for personalization rule. Personalization rule encompasses context for
 * the personalization technology to interpret and use in the personalization process
 */
@Getter
public abstract class PersonalizationRule<T> {
    private T context;

    @JsonProperty("personalizationType")
    public String getPersonalizationTypeCode() {
        return this.getClass().getSimpleName().replace("PersonalizationRule", "").toUpperCase();
    }
}
