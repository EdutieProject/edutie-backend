package com.edutie.domain.core.learning.learningexperience.entities.paragraphs;

import com.edutie.domain.core.common.paragraph.OrderedParagraph;
import com.edutie.domain.core.common.paragraph.TextContent;
import com.edutie.domain.core.learning.learningexperience.identities.NotesParagraphId;
import jakarta.persistence.Entity;

@Entity
public class NotesTextParagraph extends OrderedParagraph<TextContent, NotesParagraphId> {
}
