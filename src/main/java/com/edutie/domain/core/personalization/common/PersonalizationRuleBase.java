package com.edutie.domain.core.personalization.common;

import lombok.AllArgsConstructor;

/**
 * A base class for personalization rule. Personalization rule encompasses context for
 * the personalization technology to interpret and use in the personalization process
 */
@AllArgsConstructor
public abstract class PersonalizationRuleBase<T> implements PersonalizationRule<T> {
    private T context;

    @Override
    public T getPersonalizationContext() {
        return context;
    }
}
