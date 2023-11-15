package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.student.identities.LearningParametersId;
import com.edutie.edutiebackend.domain.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.common.studenttraits.Skill;
import com.edutie.edutiebackend.domain.student.exceptions.TraitTrackerNotFoundException;
import com.edutie.edutiebackend.domain.student.valueobjects.TraitTracker;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * Class responsible for handling all parameters of student's
 * knowledge. Uses TraitTrackers to track student traits.
 * @see TraitTracker
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: Handle exceptions across functions!!!
public class LearningParameters extends EntityBase<LearningParametersId> {
    private TraitTracker<Skill> skillTraitTracker;
    private TraitTracker<Intelligence> intelligenceTraitTracker;

    /**
     * Adds progress indicator to the current tracking parameter. If no tracking
     * exists, initializes it with provided progress value.
     * @param traitClass class of trait
     * @param trait concrete trait
     * @param progressIndicator amount of progress as floating point number
     * @param <T> type of trait
     * @throws TraitTrackerNotFoundException Exception thrown when provided arguments cannot find any trait tracker
     */
    public <T extends Enum<T>> void adapt(Class<T> traitClass, T trait, double progressIndicator) throws TraitTrackerNotFoundException {
        var tracker = findTrackerByTraitClass(traitClass)
                .orElseThrow(TraitTrackerNotFoundException::new);
        tracker.params().merge(trait, progressIndicator, Double::sum);
    }

    /**
     * Retrieves the actual parameter value of the tracked trait.
     * @param traitClass class of the trait
     * @param trait concrete trait
     * @return value of tracking parameter as optional Double
     * @param <T> Type of trait
     * @throws TraitTrackerNotFoundException Exception thrown when provided arguments cannot find any trait tracker
     */
    public <T extends Enum<T>> Optional<Double> getParameter(Class<T> traitClass, T trait) throws TraitTrackerNotFoundException {
        var tracker = findTrackerByTraitClass(traitClass)
                .orElseThrow(TraitTrackerNotFoundException::new);
        return Optional.ofNullable(tracker.params().get(trait));
    }

    /**
     *  Resets tracking of given trait within given trait class by setting its parameter
     *  value to 0. Does nothing if trait is not being tracked.
     * @param trait trait to reset
     * @param <T> trait class
     * @throws TraitTrackerNotFoundException Exception thrown when provided arguments cannot find any trait tracker
     */
    public <T extends Enum<T>> void resetTracking(Class<T> traitClass, T trait) throws TraitTrackerNotFoundException {
        var tracker = findTrackerByTraitClass(traitClass)
                .orElseThrow(TraitTrackerNotFoundException::new);
        if(tracker.params().containsKey(trait))
            tracker.params().put(trait, 0.0);
    }

    /**
     * sets the given trait tracking parameter to provided value
     * @param traitClass class of given trait
     * @param trait concrete trait
     * @param value value to set
     * @param <T> type of trait
     * @throws TraitTrackerNotFoundException Exception thrown when provided arguments cannot find any trait tracker
     */
    public <T extends Enum<T>> void setTracking(Class<T> traitClass, T trait, double value) throws TraitTrackerNotFoundException {
        var tracker = findTrackerByTraitClass(traitClass)
                .orElseThrow(TraitTrackerNotFoundException::new);
        tracker.params().put(trait, value);
    }


    /**
     * Function responsible for finding the desired tracker
     * @param traitClass trait class of tracker to find
     * @return optional reference of the desired tracker
     * @param <T> trait e.g. Intelligence
     */
    private <T extends Enum<T>> Optional<TraitTracker<T>> findTrackerByTraitClass(Class<T> traitClass) {
        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == TraitTracker.class) {
                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                Type[] typeArguments = genericType.getActualTypeArguments();

                if (typeArguments.length > 0 && typeArguments[0] == traitClass) {
                    try {
                        field.setAccessible(true);
                        return Optional.ofNullable((TraitTracker<T>) field.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return Optional.empty();
    }
}
