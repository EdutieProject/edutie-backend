package com.edutie.domain.service.studyprogram.progressindication.lesson;

import com.edutie.domain.core.learning.student.Student;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

public interface LessonProgressIndicationService {
    WrapperResult<LessonProgressState> getLessonProgressState(Lesson lesson, Student student);
}
