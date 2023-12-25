package com.edutie.edutiebackend.domain.core.common.rule;

import lombok.AllArgsConstructor;
import lombok.Getter;

//TODO: static factory method of errors

/**
 *
 */
@AllArgsConstructor
@Getter
public class Error {
    Rule<?> brokenRule;
    String message;
}
