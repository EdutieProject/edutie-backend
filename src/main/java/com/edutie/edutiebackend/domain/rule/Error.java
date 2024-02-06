package com.edutie.edutiebackend.domain.rule;

import lombok.Getter;

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
        this.code = brokenRule.getClass().getSimpleName();
    }

    public Error(String code, String message)
    {
        this.message = message;
        this.code = code;
    }
}
