package com.edutie.domain.core.learning.learningresult.entities.submission.common;

import com.edutie.domain.core.common.paragraph.OrderedParagraph;
import com.edutie.domain.core.common.paragraph.TextContent;
import com.edutie.domain.core.learning.learningresult.identities.ActivitySolutionParagraphId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class ActivitySolutionParagraph extends OrderedParagraph<TextContent, ActivitySolutionParagraphId> {
}
