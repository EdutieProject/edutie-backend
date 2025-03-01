package com.edutie.domain.core.learning.learningexperience.entities.paragraphs;

import com.edutie.domain.core.common.paragraph.OrderedParagraph;
import com.edutie.domain.core.learning.learningexperience.identities.NotesParagraphId;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class NotesVisualisationParagraph extends OrderedParagraph<Visualisation, NotesParagraphId> {
}
