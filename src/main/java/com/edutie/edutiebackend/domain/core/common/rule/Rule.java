package com.edutie.edutiebackend.domain.core.common.rule;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @param <T>
 */
public interface Rule<T> {
    List<RuleError> check(T object);

    static <U extends Rule<T>, T> Result<T> validate(Class<U> ruleClass, T ruleObject) {
        List<RuleError> ruleErrors;
        try {
            U rule = ruleClass.getConstructor().newInstance();
            ruleErrors = new ArrayList<>(rule.check(ruleObject));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return new Result<>(ruleObject, ruleErrors);
    }

    static <U extends Rule<T>, T> Result<T> validateAll(List<Class<U>> ruleClasses, T ruleObject)
    {
        List<RuleError> ruleErrors = new ArrayList<>();
        for (var ruleClass : ruleClasses)
        {
            Result<T> result = validate(ruleClass, ruleObject);
            try {
                U rule = ruleClass.getConstructor().newInstance();
                ruleErrors.addAll(rule.check(ruleObject));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return new Result<>(ruleObject, ruleErrors);
    }
}
