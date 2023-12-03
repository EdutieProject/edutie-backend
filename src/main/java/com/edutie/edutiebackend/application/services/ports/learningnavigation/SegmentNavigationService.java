package com.edutie.edutiebackend.application.services.ports.learningnavigation;

import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

import java.util.Set;

public interface SegmentNavigationService {
    Set<LessonSegmentId> getBestLearningPathFor(StudentId studentId, LessonId lessonId);
    LessonSegmentId getBestAvailableSegmentFor(StudentId studentId, LessonId lessonId);
}
