package com.edutie.backend.application.learning.lesson.implementation;

import com.edutie.backend.application.learning.lesson.LessonsForStudentFromCourseQueryHandler;
import com.edutie.backend.application.learning.lesson.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.application.common.UseCaseHandlerBase;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonsForStudentFromCourseQueryHandlerImplementation
        extends UseCaseHandlerBase implements LessonsForStudentFromCourseQueryHandler {
    private final LessonPersistence lessonPersistence;
    @Override
    public WrapperResult<List<LessonView>> handle(LessonsForStudentFromCourseQuery query) {
        LOGGER.info("Retrieving lessons for course of id {} for student of id {}",
                query.courseId().identifierValue(),
                query.studentId().identifierValue());
        List<Lesson> lessons = lessonPersistence.getAllOfCourseId(query.courseId());
        //TODO: compute "done" property in lesson view. Perhaps add mappedBy property for Lesson
        return WrapperResult.successWrapper(lessons.stream().map(o -> new LessonView(o, false)).collect(Collectors.toList()));
    }
}
