package com.edutie.domain.core.learning.learningexperience.entities.paragraphs;

import com.edutie.domain.core.common.paragraph.Paragraph;
import com.edutie.domain.core.common.paragraph.TextContent;
import com.edutie.domain.core.learning.learningexperience.identities.ActivityParagraphId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class ActivityTextParagraph extends Paragraph<TextContent, ActivityParagraphId> {
}
