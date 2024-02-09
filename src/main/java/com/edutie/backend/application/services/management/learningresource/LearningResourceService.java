package com.edutie.backend.application.services.management.learningresource;

import java.util.Set;

import com.edutie.backend.application.services.common.servicebase.GenericCrudService;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;

public interface LearningResourceService extends GenericCrudService<LearningResource, LearningResourceId> {
    /**
     * @param lessonSegmentId
     * @return
     */
    Set<LearningResource> getAllOfLessonSegment(LessonSegmentId lessonSegmentId);
    /**
     * @param lessonSegmentId
     * @return
     */
    Set<LearningResource> getWithBestResults(LessonSegmentId lessonSegmentId);
    /**
     * @param learningResource
     * @param lessonSegmentId
     * @return
     */
    LearningResource createInLessonSegment(LearningResource learningResource, LessonSegmentId lessonSegmentId);
}
