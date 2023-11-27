package com.edutie.edutiebackend.application.services.ports.assessment;

import com.edutie.edutiebackend.application.models.LearningEffect;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.LearningReport;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;

public interface LearningResultAssessmentService {

    LearningEffect assessExerciseReport(LessonSegmentId lessonSegmentId, LearningReport learningReport);
}
