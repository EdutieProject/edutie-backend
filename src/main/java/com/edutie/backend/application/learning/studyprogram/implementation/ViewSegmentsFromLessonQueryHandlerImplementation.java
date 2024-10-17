package com.edutie.backend.application.learning.studyprogram.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.studyprogram.ViewSegmentsFromLessonQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.ViewSegmentsFromLessonQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.SegmentView;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViewSegmentsFromLessonQueryHandlerImplementation extends HandlerBase implements ViewSegmentsFromLessonQueryHandler {
    private final StudentPersistence studentPersistence;
    private final LearningResultPersistence learningResultPersistence;
    private final LessonPersistence lessonPersistence;

    @Override
    public WrapperResult<List<SegmentView>> handle(ViewSegmentsFromLessonQuery query) {
        log.info("Retrieving segments from lesson of id {} for student user of id {}", query.lessonId().identifierValue(), query.studentUserId().identifierValue());
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        Lesson lesson = lessonPersistence.getById(query.lessonId()).getValue();
        return WrapperResult.successWrapper(
                lesson.getSegments().stream().map(
                        segment -> {
                            if (segment.getLearningResourceDefinitionId() == null) {
                                return new SegmentView(segment, -1, -1, false);
                            }
                           List<LearningResult> learningResults = learningResultPersistence.getLearningResultsOfStudentByLearningResourceDefinitionId(
                                    student.getId(), segment.getLearningResourceDefinitionId()
                            ).getValue();
                            int successResultsCount = (int) learningResults.stream().filter(LearningResult::isSuccessful).count();
                            return new SegmentView(segment,
                                    learningResults.size(),
                                    successResultsCount,
                                    successResultsCount > 0);
                        }
                ).collect(Collectors.toList())
        );
    }
}
