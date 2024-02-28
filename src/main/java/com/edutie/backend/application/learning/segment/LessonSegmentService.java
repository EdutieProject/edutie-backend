package com.edutie.backend.application.learning.segment;

import com.edutie.backend.application.learning.segment.viewmodels.LessonSegmentView;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;

import java.util.List;

public interface LessonSegmentService {
    List<LessonSegmentView> getSegmentsOfLessonForStudent(LessonId lessonId, StudentId studentId);
}
