package com.edutie.domain.core.learning.learningexperience.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Strongly typed text content for paragraphs.
 *
 * @param text
 */
@Embeddable
public record TextContent(@JsonValue @Column(columnDefinition = "TEXT") String text) implements Serializable {
}
