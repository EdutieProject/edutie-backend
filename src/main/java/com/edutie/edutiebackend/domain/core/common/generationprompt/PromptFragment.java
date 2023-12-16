package com.edutie.edutiebackend.domain.core.common.generationprompt;

import jakarta.persistence.Embeddable;

/**
 * The prompt record should be used when a given text is
 * designed to be used with AI generation.
 * @param text text injected into prompt for generation.
 */
@Embeddable
public record PromptFragment(String text) {
    public PromptFragment()
    {
        this("DEFAULT");
    }
}