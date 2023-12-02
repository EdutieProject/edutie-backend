package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.lesson.Lesson;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;

import java.util.Set;

public interface LessonService extends GenericCrudService<Lesson, LessonId> {
    Set<Lesson> getAllFromCourse(CourseId courseId);
    Lesson assignPreviousLesson(LessonId lessonId);
    Lesson assignNextLesson(LessonId lessonId);
    Lesson assignNextLessons(Set<LessonId> lessonIds);
    Lesson createLessonInCourse(CourseId courseId);
    Lesson createLessonAsNext(LessonId lessonId);
}
