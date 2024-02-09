package com.edutie.backend.application.services.learningnavigation.resource;

import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.domain.learner.student.identities.StudentId;

import java.util.Set;

public interface ResourceNavigationService {
    /**
     * @param lessonSegmentId
     * @param studentId
     * @return
     */
    Set<LearningResourceId> getBestOfLessonSegment(LessonSegmentId lessonSegmentId, StudentId studentId);
}
