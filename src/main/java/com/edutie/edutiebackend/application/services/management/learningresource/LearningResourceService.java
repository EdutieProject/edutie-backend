package com.edutie.edutiebackend.application.services.management.learningresource;

import com.edutie.edutiebackend.application.services.common.servicebase.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;

import java.util.Set;

public interface LearningResourceService extends GenericCrudService<LearningResource, LearningResourceId> {
    Set<LearningResource> getAllOfLessonSegment(LessonSegmentId lessonSegmentId);
    Set<LearningResource> getWithBestResults(LessonSegmentId lessonSegmentId);
    LearningResource createInLessonSegment(LearningResource learningResource, LessonSegmentId lessonSegmentId);
}
