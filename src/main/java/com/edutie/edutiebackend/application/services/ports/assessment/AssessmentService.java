package com.edutie.edutiebackend.application.services.ports.assessment;

import com.edutie.edutiebackend.application.data.viewmodels.LearningEffect;

import java.util.UUID;

public interface AssessmentService {

    LearningEffect assessExerciseReport(UUID lessonSegmentId, String learningReportString);
}
