package com.edutie.backend.application.services.personalization.resource;

import com.edutie.backend.application.services.personalization.resource.commands.GenerateResourceCommand;
import com.edutie.backend.domain.core.learningresource.LearningResource;

/**
 * Interface responsible for creating learning resources
 */
public interface ResourceGenerationService {
    /**
     * Provides a learning resource optimized for a given student.
     * Implementation uses other methods that this
     * @param command Generate Resource Command
     * @return created Learning Resource object
     * @see GenerateResourceCommand
     */
    LearningResource generateForStudent(GenerateResourceCommand command);
}
