package com.edutie.edutiebackend.domain.rule;

import java.util.List;
import java.util.ArrayList;

/**
 * Rule interface meant to be implemented
 * by the ruleClass used by static validation method.
 * @param <T> type of ruleClass-checked object
 */
//TODO: rule framework refactoring / use existing validation framework
public interface Rule<T> {
    List<Error> check(T object);

    /**
     * Validate a ruleClass of given object
     * @param ruleClass class of the ruleClass
     * @param ruleObject validated object
     * @return Result of type T
     * @param <U> type of ruleClass class
     * @param <T> type of object to validate
     */
    static <U extends Rule<T>, T> Result validate(Class<U> ruleClass, T ruleObject) {
        List<Error> errors;
        try {
            U rule = ruleClass.getConstructor().newInstance();
            errors = new ArrayList<>(rule.check(ruleObject));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return Result.fromErrorList(errors);
    }

    /**
     * Validates object according to the list of rules
     * @param ruleClasses list of ruleClass classes
     * @param ruleObject object to validate
     * @return Result of type T
     * @param <U> type of ruleClass class
     * @param <T> type of object to validate
     */
    static <U extends Rule<T>, T> Result validate(List<Class<U>> ruleClasses, T ruleObject)
    {
        List<Error> errors = new ArrayList<>();
        for (var ruleClass : ruleClasses)
        {
            try {
                U rule = ruleClass.getConstructor().newInstance();
                errors.addAll(rule.check(ruleObject));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return Result.fromErrorList(errors);
    }

    static <U extends Rule<T>, T> Result validate(List<Validation<U, T>> validations)
    {
        List<Error> errors = new ArrayList<>();
        for (var validation : validations)
        {
            try {
                U rule = validation.ruleClass().getConstructor().newInstance();
                errors.addAll(rule.check(validation.validatedObject()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return Result.fromErrorList(errors);
    }
}
