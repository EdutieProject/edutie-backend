package com.edutie.edutiebackend.application.services.assessment.implementation;

import com.edutie.edutiebackend.application.services.assessment.AssessmentService;
import com.edutie.edutiebackend.application.services.assessment.requests.ReportAssessmentRequest;
import com.edutie.edutiebackend.application.viewmodels.LearningEffect;
import org.springframework.stereotype.Service;

@Service
public class DefaultAssessmentService implements AssessmentService {
    /**
     * @param request
     * @return
     */
    @Override
    public LearningEffect assessExerciseReport(ReportAssessmentRequest request) {
        return null;
    }
}
