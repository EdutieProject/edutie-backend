package com.edutie.domain.core.learning.learningexperience.entities.activity.common;

import jakarta.persistence.Embeddable;

@Embeddable
public record AnswerOption(
        String text, boolean isCorrect
) {
}
