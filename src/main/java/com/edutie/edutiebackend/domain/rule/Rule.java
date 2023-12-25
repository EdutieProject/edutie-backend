package com.edutie.edutiebackend.domain.rule;

import java.util.List;
import java.util.ArrayList;

/**
 * Rule interface meant to be implemented
 * by the rule used by static validation method.
 * @param <T> type of rule-checked object
 */
public interface Rule<T> {
    List<RuleError> check(T object);

    /**
     * Validate a rule of given object
     * @param ruleClass class of the rule
     * @param ruleObject validated object
     * @return Result of type T
     * @param <U> type of rule class
     * @param <T> type of object to validate
     */
    static <U extends Rule<T>, T> Result<T> validate(Class<U> ruleClass, T ruleObject) {
        List<RuleError> ruleErrors;
        try {
            U rule = ruleClass.getConstructor().newInstance();
            ruleErrors = new ArrayList<>(rule.check(ruleObject));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return Result.fromErrorList(ruleErrors, ruleObject);
    }

    /**
     * Validates object according to the list of rules
     * @param ruleClasses list of rule classes
     * @param ruleObject object to validate
     * @return Result of type T
     * @param <U> type of rule class
     * @param <T> type of object to validate
     */
    static <U extends Rule<T>, T> Result<T> validateAll(List<Class<U>> ruleClasses, T ruleObject)
    {
        List<RuleError> ruleErrors = new ArrayList<>();
        for (var ruleClass : ruleClasses)
        {
            try {
                U rule = ruleClass.getConstructor().newInstance();
                ruleErrors.addAll(rule.check(ruleObject));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return Result.fromErrorList(ruleErrors, ruleObject);
    }
}
