package com.edutie.edutiebackend.domain.core.learningresult.valueobjects;

import com.edutie.edutiebackend.domain.core.learningresult.enums.FeedbackType;

public record Feedback(
        String text,
        FeedbackType type
) {
}
