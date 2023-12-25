package com.edutie.edutiebackend.domain.core.common.rule;

/**
 *
 */
public class RuleViolationException extends RuntimeException{
    public RuleViolationException(String msg)
    {
        super(msg);
    }
}
