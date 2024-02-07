package validation;

import validation.rules.CompoundRule;
import validation.rules.Rule;

import java.util.List;
import java.util.ArrayList;

/**
 * Functional class providing necessary behaviour for rule validation
 */
//TODO: rule framework refactoring / use existing validation framework
public final class RuleEnforcer {
    /**
     * Validate a ruleClass of given object
     * @param ruleClass class of the ruleClass
     * @param ruleObject validated object
     * @return Result of type T
     * @param <U> type of ruleClass class
     * @param <T> type of object to validate
     */
    public static <U extends Rule<T>, T> Result validate(Class<U> ruleClass, T ruleObject) {
        List<Error> errors;
        try {
            U rule = ruleClass.getConstructor().newInstance();
            errors = new ArrayList<>(rule.check(ruleObject));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return Result.fromErrorList(errors);
    }

    public static <U extends CompoundRule<T, S>, T, S> Result validate(Class<U> ruleClass, T first, S second) {
        List<Error> errors;
        try {
            U rule = ruleClass.getConstructor().newInstance();
            errors = new ArrayList<>(rule.check(first, second));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return Result.fromErrorList(errors);
    }
}
