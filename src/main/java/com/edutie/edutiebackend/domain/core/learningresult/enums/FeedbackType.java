package com.edutie.edutiebackend.domain.core.learningresult.enums;

import lombok.Getter;

/**
 * Enum describing general opinion described by feedback.
 * Serves as a one-off alternative for user to know about
 * feedback opinion.
 */
//TODO: broaden feedback types
@Getter
public enum FeedbackType {
    POSITIVE("Positive"),
    NEGATIVE("Negative");

    private String code;
    FeedbackType(String code)
    {
        this.code = code;
    }
}
