package com.edutie.backend.application.services.assessment;

import com.edutie.backend.application.services.assessment.requests.ReportAssessmentRequest;
import com.edutie.backend.application.viewmodels.LearningEffect;

public interface AssessmentService {

    /**
     * @param request
     * @return
     */
    LearningEffect assessExerciseReport(ReportAssessmentRequest request);
}
