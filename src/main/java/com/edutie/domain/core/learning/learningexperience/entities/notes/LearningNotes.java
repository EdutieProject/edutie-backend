package com.edutie.domain.core.learning.learningexperience.entities.notes;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.learning.common.paragraph.OrderedParagraph;
import com.edutie.domain.core.learning.learningexperience.entities.notes.paragraphs.NotesSimpleParagraph;
import com.edutie.domain.core.learning.learningexperience.entities.notes.paragraphs.NotesVisualisationParagraph;
import com.edutie.domain.core.learning.learningexperience.identities.LearningNotesId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class LearningNotes extends EntityBase<LearningNotesId> {
    @OneToMany(targetEntity = NotesSimpleParagraph.class, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<NotesSimpleParagraph> simpleParagraphs;
    @OneToMany(targetEntity = NotesVisualisationParagraph.class, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<NotesVisualisationParagraph> visualisationParagraphs;

    @JsonProperty
    public List<? extends OrderedParagraph<?, ?>> getParagraphs() {
        List<OrderedParagraph<?, ?>> allParagraphs = new ArrayList<>();
        allParagraphs.addAll(simpleParagraphs);
        allParagraphs.addAll(visualisationParagraphs);
        allParagraphs.sort(Comparator.comparingInt(OrderedParagraph::getOrdinal));
        return allParagraphs;
    }
}
