package validation;

import lombok.Getter;
import validation.rules.Rule;

/**
 *
 */
@Getter
public class Error {
    String message;
    String code;

    public Error(Rule<?> brokenRule, String message)
    {
        this.message = message;
        this.code = brokenRule.getClass().getSimpleName() + "Violation";
    }

    public Error(String code, String message)
    {
        this.message = message;
        this.code = code;
    }
}
