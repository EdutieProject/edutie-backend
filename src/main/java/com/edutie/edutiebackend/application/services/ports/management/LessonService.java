package com.edutie.edutiebackend.application.services.ports.management;

import com.edutie.edutiebackend.application.services.ports.common.GenericCrudService;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;
import com.edutie.edutiebackend.domain.core.lesson.Lesson;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;

import java.util.Set;

public interface LessonService extends GenericCrudService<Lesson, LessonId> {
    Set<Lesson> getAllFromCourse(CourseId courseId);
    Lesson assignPreviousLesson(LessonId lessonId, LessonId prevLessonId);
    Lesson assignNextLesson(LessonId lessonId, LessonId nextLessonId);
    Lesson assignNextLessons(LessonId lessonId, Set<LessonId> lessonIds);
    Lesson createInCourse(Lesson lesson, CourseId courseId);
    Lesson createLessonAsNext(Lesson lesson, LessonId lessonId);
}
