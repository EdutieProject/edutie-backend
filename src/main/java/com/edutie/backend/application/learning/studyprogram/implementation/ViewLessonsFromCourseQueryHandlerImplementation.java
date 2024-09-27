package com.edutie.backend.application.learning.studyprogram.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.studyprogram.ViewLessonsFromCourseQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.ViewLessonsFromCourseQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.LessonView;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domainservice.studyprogram.progressindication.lesson.LessonProgressIndicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViewLessonsFromCourseQueryHandlerImplementation extends HandlerBase implements ViewLessonsFromCourseQueryHandler {
    private final LessonPersistence lessonPersistence;
    private final StudentPersistence studentPersistence;
    private final LessonProgressIndicationService lessonProgressIndicationService;

    @Override
    public WrapperResult<List<LessonView>> handle(ViewLessonsFromCourseQuery query) {
        log.info("Retrieving lessons for course of id {} for student of id {}", query.courseId(), query.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        List<Lesson> lessons = lessonPersistence.getAllOfCourseId(query.courseId()).getValue();

        return WrapperResult.successWrapper(lessons.stream().map(lesson ->
                        new LessonView(lesson, lessonProgressIndicationService.getLessonProgressState(lesson, student).getValue())
                ).collect(Collectors.toList())
        );
    }
}
