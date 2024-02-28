package com.edutie.backend.application.learning.resource;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

import java.util.List;

public interface LearningResourceService {
    List<LearningResource> getAllResourcesOfSegment(LessonSegmentId lessonSegmentId);
}
