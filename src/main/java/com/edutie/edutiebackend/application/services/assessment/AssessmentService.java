package com.edutie.edutiebackend.application.services.assessment;

import com.edutie.edutiebackend.application.services.assessment.requests.ReportAssessmentRequest;
import com.edutie.edutiebackend.application.viewmodels.LearningEffect;

import java.util.UUID;

public interface AssessmentService {

    LearningEffect assessExerciseReport(ReportAssessmentRequest request);
}
