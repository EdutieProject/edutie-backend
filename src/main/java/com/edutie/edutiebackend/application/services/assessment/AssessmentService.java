package com.edutie.edutiebackend.application.services.assessment;

import com.edutie.edutiebackend.application.services.assessment.requests.ReportAssessmentRequest;
import com.edutie.edutiebackend.application.viewmodels.LearningEffect;

public interface AssessmentService {

    /**
     * @param request
     * @return
     */
    LearningEffect assessExerciseReport(ReportAssessmentRequest request);
}
