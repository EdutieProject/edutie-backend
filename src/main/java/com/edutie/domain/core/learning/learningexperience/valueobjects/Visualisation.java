package com.edutie.domain.core.learning.learningexperience.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record Visualisation(
        @Convert(converter = VisualisationType.Converter.class) VisualisationType visualisationType,
        @Column(columnDefinition = "TEXT") String code
) implements Serializable {
    public Visualisation(String code) {
        this(VisualisationType.MERMAID, code);
    }
}
