package com.edutie.backend.application.services.assessment.requests;

import java.util.UUID;

public record ReportAssessmentRequest(UUID userId, UUID lessonSegmentId, String learningReportString) {
}
