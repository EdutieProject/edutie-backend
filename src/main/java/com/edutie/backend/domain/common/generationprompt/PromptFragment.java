package com.edutie.backend.domain.common.generationprompt;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

/**
 * The prompt record should be used when a given text is
 * designed to be used with AI generation.
 * @param text text injected into prompt for generation.
 */
@Embeddable
public record PromptFragment(@JsonValue String text) {
    /**
     * Default constructor should not be used and is
     * added because of persistence purposes. Recommended way of creating
     * PromptFragments is by static factory method named 'of'.
     */
    public PromptFragment()
    {
        this("");
    }
    public static PromptFragment of(String text)
    {
        return new PromptFragment(text);
    }
}