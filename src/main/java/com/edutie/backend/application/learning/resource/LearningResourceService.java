package com.edutie.backend.application.learning.resource;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

import java.util.List;

/**
 * Service interface for managing learning resources in the educational learning context.
 */
public interface LearningResourceService {

    /**
     * Retrieves a list of all learning resources associated with a specific lesson segment.
     *
     * @param lessonSegmentId The identifier of the lesson segment.
     * @return A list of learning resources related to the specified lesson segment.
     */
    List<LearningResource> getAllResourcesOfSegment(LessonSegmentId lessonSegmentId);
}
