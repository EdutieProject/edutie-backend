package com.edutie.domain.core.common.paragraph;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Strongly typed text content for paragraphs.
 *
 * @param text
 */
@Embeddable
public record TextContent(
        @Convert(converter = TextContentType.Converter.class) TextContentType textContentType,
        @Column(columnDefinition = "TEXT") String text
) implements Serializable {
}
