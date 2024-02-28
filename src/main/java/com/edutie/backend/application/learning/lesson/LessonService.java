package com.edutie.backend.application.learning.lesson;

import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;

import java.util.List;

public interface LessonService {
    List<LessonView> getLessonsOfCourseForStudent(CourseId courseId, StudentId studentId);
}
