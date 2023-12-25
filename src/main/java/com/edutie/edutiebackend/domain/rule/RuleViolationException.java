package com.edutie.edutiebackend.domain.rule;

/**
 * Exception thrown when the rules are violated
 * and rule framework is misused and forced to
 * omit rule validation.
 */
public class RuleViolationException extends RuntimeException{
    public RuleViolationException(String msg)
    {
        super(msg);
    }
}
