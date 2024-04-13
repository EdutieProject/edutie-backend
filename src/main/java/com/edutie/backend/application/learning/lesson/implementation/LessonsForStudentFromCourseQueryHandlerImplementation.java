package com.edutie.backend.application.learning.lesson.implementation;

import com.edutie.backend.application.learning.lesson.LessonsForStudentFromCourseQueryHandler;
import com.edutie.backend.application.learning.lesson.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonsForStudentFromCourseQueryHandlerImplementation extends HandlerBase implements LessonsForStudentFromCourseQueryHandler {
    private final LessonPersistence lessonPersistence;
    private final StudentPersistence studentPersistence;
    @Override
    public WrapperResult<List<LessonView>> handle(LessonsForStudentFromCourseQuery query) {
        LOGGER.info("Retrieving lessons for course of id {} for student of id {}",
                query.courseId().identifierValue(),
                query.studentUserId().identifierValue());
        Student student = studentPersistence.getByUserId(query.studentUserId());
        List<Lesson> lessons = lessonPersistence.getAllOfCourseId(query.courseId());
        //TODO: note that current implementation signs which lesson is "touched".
        return WrapperResult.successWrapper(
                lessons.stream().map(o ->
                        new LessonView(o, student.getLearningHistory().stream().map(
                                res -> res.getSegment().getLesson()).collect(Collectors.toSet()).contains(o)
                        )
                ).collect(Collectors.toList()));
    }
}
