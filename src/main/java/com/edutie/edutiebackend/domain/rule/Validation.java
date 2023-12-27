package com.edutie.edutiebackend.domain.rule;

public record Validation<U extends Rule<T>, T> (Class<U> ruleClass, T validatedObject){
    public static <U extends Rule<T>, T> Validation<U, T> of(Class<U> rule, T validatedObject)
    {
        return new Validation<>(rule, validatedObject);
    }
}
