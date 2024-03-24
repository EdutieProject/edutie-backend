package com.edutie.backend.application.learning.lesson.implementation;

import com.edutie.backend.application.learning.lesson.LessonService;
import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.infrastucture.persistence.contexts.studyprogram.LessonPersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultLessonService implements LessonService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private LessonPersistenceContext lessonPersistenceContext;

    public DefaultLessonService(LessonPersistenceContext lessonPersistenceContext) {
        this.lessonPersistenceContext = lessonPersistenceContext;
    }

    /**
     * Retrieves a list of lesson views for a specific course and student.
     *
     * @param courseId  The identifier of the course.
     * @param studentId The identifier of the student.
     * @return A list of lesson views for the specified course and student.
     */
    @Override
    public List<LessonView> getLessonsOfCourseForStudent(CourseId courseId, StudentId studentId) {
        LOGGER.info("Retrieving lessons for course of id {} for student of id {}", courseId.identifierValue(), studentId.identifierValue());
        List<Lesson> lessons = lessonPersistenceContext.getAllOfCourseId(courseId);
        //TODO: compute "done" property in lesson view
        return lessons.stream().map(o->new LessonView(o, false)).collect(Collectors.toList());
    }
}
