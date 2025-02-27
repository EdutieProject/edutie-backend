package com.edutie.domain.core.learning.learningexperience.entities.notes.paragraphs;

import com.edutie.domain.core.learning.common.paragraph.OrderedParagraph;
import com.edutie.domain.core.learning.learningexperience.identities.LearningNotesParagraphId;
import com.edutie.domain.core.learning.learningexperience.valueobjects.TextContent;
import jakarta.persistence.Entity;

@Entity
public class NotesSimpleParagraph extends OrderedParagraph<TextContent, LearningNotesParagraphId> {
}
