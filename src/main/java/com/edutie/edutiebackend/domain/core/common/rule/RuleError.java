package com.edutie.edutiebackend.domain.core.common.rule;

import lombok.Getter;

/**
 *
 */
@Getter
public class RuleError {
    Rule<?> brokenRule;
    String message;
    String code;

    public RuleError(Rule<?> brokenRule, String message)
    {
        this.brokenRule = brokenRule;
        this.message = message;
        this.code = brokenRule.getClass().getName();
    }

    public RuleError(String code, String message)
    {
        this.message = message;
        this.code = code;
    }
}
