package com.edutie.backend.application.learning.ancillaries.viewmodels;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.studyprogram.course.Course;


public record LatestActivityView(LearningResult latestLearningResult, CourseView latestCourseView) {
    public record CourseView(Course course, double progressIndicator) {
    }
}
