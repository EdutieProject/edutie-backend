package com.edutie.edutiebackend.domain.student.entites.base;

import com.edutie.edutiebackend.domain.common.base.EntityBase;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.EnumMap;

/**
 * Generic trait profile made for parametrizing one's traits.
 * @implNote Trait profile does not have to contain all traits at once.
 * Traits are added during usage.
 * @param <TTrait> The student trait to track
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class StudentTraitProfile<TTrait extends Enum<TTrait>, TId extends Serializable> extends EntityBase<TId> {
    protected EnumMap<TTrait, Double> parameters;

    /**
     * Adds the given amount of points to the tracker to the given trait. If trait is
     * not tracked, initializes the tracking with given value.
     * @param trait chosen trait
     * @param points number of points to add
     */
    public void adjust(TTrait trait, double points)
    {
        if(parameters.containsKey(trait))
        {
            var current = parameters.get(trait);
            parameters.put(trait, current + points);
        }
        else
        {
            set(trait, points);
        }
    }

    /**
     * Sets the current trait to be parametrized with a given value.
     * @param trait chosen trait
     * @param value value to be set
     */
    public void set(TTrait trait, double value)
    {
        parameters.put(trait, value);
    }

    /**
     * Deletes parameters and their keys. Therefore, recreates a profile.
     */
    public void resetHard()
    {
        parameters.clear();
    }

    /**
     * Assigns 0 points to all parameters that are currently tracked.
     */
    public void resetSoft()
    {
        parameters.replaceAll((t, v) -> 0.0);
    }

    /**
     * Resets parameter if it is currently tracked to 0 points. Does nothing
     * if it is not tracked
     * @param trait trait to be reset
     */
    public void resetParameter(TTrait trait)
    {
        parameters.replace(trait, 0.0);
    }

}
