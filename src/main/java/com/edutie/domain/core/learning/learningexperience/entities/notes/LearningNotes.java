package com.edutie.domain.core.learning.learningexperience.entities.notes;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.paragraph.OrderedParagraph;
import com.edutie.domain.core.common.paragraph.TextContent;
import com.edutie.domain.core.learning.learningexperience.entities.paragraphs.NotesTextParagraph;
import com.edutie.domain.core.learning.learningexperience.entities.paragraphs.NotesVisualisationParagraph;
import com.edutie.domain.core.learning.learningexperience.identities.LearningNotesId;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class LearningNotes extends EntityBase<LearningNotesId> {
    @OneToMany(targetEntity = NotesTextParagraph.class, fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<NotesTextParagraph> textParagraphs = new ArrayList<>();
    @OneToMany(targetEntity = NotesVisualisationParagraph.class, fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<NotesVisualisationParagraph> visualisationParagraphs = new ArrayList<>();

    @JsonProperty
    public List<? extends OrderedParagraph<?, ?>> getParagraphs() {
        List<OrderedParagraph<?, ?>> allParagraphs = new ArrayList<>();
        allParagraphs.addAll(textParagraphs);
        allParagraphs.addAll(visualisationParagraphs);
        allParagraphs.sort(Comparator.comparingInt(OrderedParagraph::getOrdinal));
        return allParagraphs;
    }

    public static LearningNotes create(TextContent content, Visualisation visualisation) {
        LearningNotes notes = new LearningNotes();
        notes.setId(new LearningNotesId());
        notes.textParagraphs.add(NotesTextParagraph.create(content, 0));
        notes.visualisationParagraphs.add(NotesVisualisationParagraph.create(visualisation, 1));
        return notes;
    }
}
