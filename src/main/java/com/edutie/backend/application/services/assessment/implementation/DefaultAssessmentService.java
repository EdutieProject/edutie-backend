package com.edutie.backend.application.services.assessment.implementation;
import com.edutie.backend.application.services.assessment.requests.ReportAssessmentRequest;
import org.springframework.stereotype.Service;
import com.edutie.backend.application.services.assessment.AssessmentService;
import com.edutie.backend.application.viewmodels.LearningEffect;

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
