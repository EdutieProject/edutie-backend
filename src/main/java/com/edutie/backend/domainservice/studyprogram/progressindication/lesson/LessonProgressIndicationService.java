package com.edutie.backend.domainservice.studyprogram.progressindication.lesson;

import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import validation.WrapperResult;

public interface LessonProgressIndicationService {
    WrapperResult<LessonProgressState> getLessonProgressState(Lesson lesson, Student student);
}
