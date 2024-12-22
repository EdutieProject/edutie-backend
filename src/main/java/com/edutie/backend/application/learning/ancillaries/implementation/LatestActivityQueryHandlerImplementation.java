package com.edutie.backend.application.learning.ancillaries.implementation;

import com.edutie.backend.application.learning.ancillaries.LatestActivityQueryHandler;
import com.edutie.backend.application.learning.ancillaries.queries.LatestActivityQuery;
import com.edutie.backend.application.learning.ancillaries.viewmodels.LatestActivityView;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domainservice.studyprogram.progressindication.course.CourseProgressIndicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class LatestActivityQueryHandlerImplementation implements LatestActivityQueryHandler {
    private final LearningResultPersistence learningResultPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final StudentPersistence studentPersistence;
    private final CoursePersistence coursePersistence;
    private final CourseProgressIndicationService courseProgressIndicationService;

    @Override
    public WrapperResult<LatestActivityView> handle(LatestActivityQuery query) {
        log.info("Retrieving latest activity for student user of id {}", query.studentUserId());
        Student student = studentPersistence.getByAuthorizedUserId(query.studentUserId());
        LearningResult latestResult = student.getLatestLearningResult(learningResultPersistence).getValue();
        if (learningResourcePersistence.getById(latestResult.getSolutionSubmission().getLearningResourceId()).getValue()
                .getDefinitionType().equals(DefinitionType.DYNAMIC))
            return WrapperResult.successWrapper(new LatestActivityView(latestResult, null));
        Course latestCourse = coursePersistence.findByRelatedLearningResourceId(latestResult.getAssociatedLearningResourceId()).getValue();
        double courseProgressIndicator = courseProgressIndicationService.getCourseProgressFactor(latestCourse, student).getValue();
        return WrapperResult.successWrapper(new LatestActivityView(
                latestResult,
                new LatestActivityView.CourseView(latestCourse, courseProgressIndicator)
        ));
    }
}
