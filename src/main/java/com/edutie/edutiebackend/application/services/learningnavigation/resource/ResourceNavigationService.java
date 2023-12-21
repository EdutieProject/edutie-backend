package com.edutie.edutiebackend.application.services.learningnavigation.resource;

import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

import java.util.Set;

public interface ResourceNavigationService {
    /**
     * @param lessonSegmentId
     * @param studentId
     * @return
     */
    Set<LearningResourceId> getBestOfLessonSegment(LessonSegmentId lessonSegmentId, StudentId studentId);
}
