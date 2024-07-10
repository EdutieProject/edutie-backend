package com.edutie.backend.application.learning.lesson.implementation;

import com.edutie.backend.application.learning.lesson.LessonsForStudentFromCourseQueryHandler;
import com.edutie.backend.application.learning.lesson.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
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
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        WrapperResult<List<Lesson>> lessonsResult = lessonPersistence.getAllOfCourseId(query.courseId());
        if (lessonsResult.isFailure()) {
            LOGGER.info("Persistence error: " + lessonsResult.getError().toString());
            return lessonsResult.map(o -> null);
        }

        //TODO: note that current implementation signs which lesson is "touched".
        return lessonsResult.map(primaryResult -> primaryResult.stream().map(o ->
                        new LessonView(o, false)
                ).collect(Collectors.toList()));
    }
}
