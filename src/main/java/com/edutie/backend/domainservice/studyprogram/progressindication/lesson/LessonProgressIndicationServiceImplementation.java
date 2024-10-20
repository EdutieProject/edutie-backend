package com.edutie.backend.domainservice.studyprogram.progressindication.lesson;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class LessonProgressIndicationServiceImplementation implements LessonProgressIndicationService {
    private final LearningResultPersistence learningResultPersistence;

    @Override
    public WrapperResult<LessonProgressState> getLessonProgressState(Lesson lesson, Student student) {
        //TODO - filter #201 issue
        boolean isLessonNotBegun = lesson.getSegments().stream().filter(o -> o.getLearningResourceDefinitionId() != null)
                .allMatch(o -> learningResultPersistence
                        .getLearningResultsOfStudentByLearningResourceDefinitionId(student.getId(), o.getLearningResourceDefinitionId())
                        .getValue().isEmpty()
                );
        if (isLessonNotBegun)
            return WrapperResult.successWrapper(LessonProgressState.NONE);

        boolean isLessonFinished = lesson.getSegments().stream().filter(o -> o.getLearningResourceDefinitionId() != null).allMatch(o -> learningResultPersistence
                .getLearningResultsOfStudentByLearningResourceDefinitionId(student.getId(), o.getLearningResourceDefinitionId())
                .getValue().stream().anyMatch(LearningResult::isSuccessful)
        );
        if (isLessonFinished)
            return WrapperResult.successWrapper(LessonProgressState.DONE);

        // Default to IN_PROGRESS
        return WrapperResult.successWrapper(LessonProgressState.IN_PROGRESS);
    }
}
