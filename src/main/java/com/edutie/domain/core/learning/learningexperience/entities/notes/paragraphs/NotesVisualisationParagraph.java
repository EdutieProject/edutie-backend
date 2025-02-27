package com.edutie.domain.core.learning.learningexperience.entities.notes.paragraphs;

import com.edutie.domain.core.learning.common.paragraph.OrderedParagraph;
import com.edutie.domain.core.learning.learningexperience.identities.LearningNotesParagraphId;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import jakarta.persistence.Entity;

@Entity
public class NotesVisualisationParagraph extends OrderedParagraph<Visualisation, LearningNotesParagraphId> {
}
