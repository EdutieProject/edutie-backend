package com.edutie.edutiebackend.domain.rule;

import lombok.Getter;

/**
 *
 */
@Getter
public class RuleError {
    String message;
    String code;

    public RuleError(Rule<?> brokenRule, String message)
    {
        this.message = message;
        this.code = brokenRule.getClass().getSimpleName();
    }

    public RuleError(String code, String message)
    {
        this.message = message;
        this.code = code;
    }
}
