package com.edutie.backend.application.services.learningnavigation.resource;

import com.edutie.backend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.domain.core.student.identities.StudentId;

import java.util.Set;

public interface ResourceNavigationService {
    /**
     * @param lessonSegmentId
     * @param studentId
     * @return
     */
    Set<LearningResourceId> getBestOfLessonSegment(LessonSegmentId lessonSegmentId, StudentId studentId);
}
