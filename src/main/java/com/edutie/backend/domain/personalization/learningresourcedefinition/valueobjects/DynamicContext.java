package com.edutie.backend.domain.personalization.learningresourcedefinition.valueobjects;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DynamicContextType;

public record DynamicContext(PromptFragment text, DynamicContextType type) {
}
