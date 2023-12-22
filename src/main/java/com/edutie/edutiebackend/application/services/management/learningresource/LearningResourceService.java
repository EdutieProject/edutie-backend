package com.edutie.edutiebackend.application.services.management.learningresource;

import java.util.Set;

import com.edutie.edutiebackend.application.services.common.servicebase.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;

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
