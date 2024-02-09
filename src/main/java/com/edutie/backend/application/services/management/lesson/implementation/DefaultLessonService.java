package com.edutie.backend.application.services.management.lesson.implementation;

import java.util.Optional;
import java.util.Set;

import com.edutie.backend.application.services.management.lesson.LessonService;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import org.springframework.stereotype.Service;

@Service
public class DefaultLessonService implements LessonService {
    /**
     * @param lesson
     * @return
     */
    @Override
    public Lesson create(Lesson lesson) {
        return null;
    }

    /**
     * @param lesson
     * @return
     */
    @Override
    public Optional<Lesson> overwrite(Lesson lesson) { //Czy to ma tak działać, że jeśli nadpisujesz lekcje to wywala wszystkei z segmentu, czy czegoś nie rozumiem
        return Optional.empty();
    }

    /**
     * @param lessonId
     * @return
     */
    @Override
    public boolean delete(LessonId lessonId) {
        return false;
    }

    /**
     * @param lessonId
     * @return
     */
    @Override
    public Optional<Lesson> getById(LessonId lessonId) {
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public Set<Lesson> getAll() {
        return null;
    }

    /**
     * @param courseId
     * @return
     */
    @Override
    public Set<Lesson> getAllFromCourse(CourseId courseId) {
        return null;
    }

    /**
     * @param lessonId
     * @param prevLessonId
     * @return
     */
    @Override
    public Lesson assignPreviousLesson(LessonId lessonId, LessonId prevLessonId) {
        return null;
    }

    /**
     * @param lessonId
     * @param nextLessonId
     * @return
     */
    @Override
    public Lesson assignNextLesson(LessonId lessonId, LessonId nextLessonId) {
        return null;
    }

    /**
     * @param lessonId
     * @param lessonIds
     * @return
     */
    @Override
    public Lesson assignNextLessons(LessonId lessonId, Set<LessonId> lessonIds) {
        return null;
    }

    /**
     * @param lesson
     * @param courseId
     * @return
     */
    @Override
    public Lesson createInCourse(Lesson lesson, CourseId courseId) {
        return null;
    }

    /**
     * @param lesson
     * @param lessonId
     * @return
     */
    @Override
    public Lesson createLessonAsNext(Lesson lesson, LessonId lessonId) {
        return null;
    }
}
