package com.edutie.edutiebackend.domain.student.valueobjects;

import java.util.HashMap;

/**
 * A tracker for a given trait. Trait may be skill, intelligence or
 * sth related to student abilities.
 * @param params Traits mapped with their assessment parameters
 * @param <TTrait> Trait to track
 */
public record TraitTracker<TTrait extends Enum<TTrait>>(
        HashMap<TTrait, Double> params
) {
    public TraitTracker()
    {
        this(new HashMap<>());
    }
}
