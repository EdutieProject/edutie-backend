package com.edutie.edutiebackend.domain.core.learningresult.valueobjects;

import com.edutie.edutiebackend.domain.core.learningresult.FeedbackType;

public record Feedback(
        String text,
        FeedbackType type
) {
}
