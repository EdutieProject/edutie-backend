package com.edutie.backend.application.services.management.lesson;

import java.util.Set;

import com.edutie.backend.domain.core.course.identities.CourseId;
import com.edutie.backend.domain.core.lesson.Lesson;
import com.edutie.backend.domain.core.lesson.identities.LessonId;
import com.edutie.backend.application.services.common.servicebase.GenericCrudService;

public interface LessonService extends GenericCrudService<Lesson, LessonId> {
    Set<Lesson> getAllFromCourse(CourseId courseId);
    Lesson assignPreviousLesson(LessonId lessonId, LessonId prevLessonId);
    Lesson assignNextLesson(LessonId lessonId, LessonId nextLessonId); 
    Lesson assignNextLessons(LessonId lessonId, Set<LessonId> lessonIds);
    Lesson createInCourse(Lesson lesson, CourseId courseId);
    Lesson createLessonAsNext(Lesson lesson, LessonId lessonId);
}

/*coś mi się ten interfejs nie podoaba. Masz  
Lesson assignNextLesson(LessonId lessonId, LessonId nextLessonId); 
    Lesson assignNextLessons(LessonId lessonId, Set<LessonId> lessonIds);
    
Nie dałoby się to opisać 1 metodą?

Poza tym masz tu assign next lesson i assign previous lesson. Ja tutaj widzę koncepcję na rozpisanie listy.*/