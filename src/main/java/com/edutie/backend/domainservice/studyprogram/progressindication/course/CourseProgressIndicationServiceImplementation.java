package com.edutie.backend.domainservice.studyprogram.progressindication.course;

import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseProgressIndicationServiceImplementation implements CourseProgressIndicationService {
    private final LearningResourcePersistence learningResourcePersistence;
    private final LearningResultPersistence learningResultPersistence;

    private boolean resultsContainResultOfSameAssociatedDefinition(List<LearningResult> learningResults, LearningResult consideredResult) {
        return learningResults.stream().anyMatch(
                o -> learningResourcePersistence.getById(o.getAssociatedLearningResourceId()).getValue().getDefinitionId()
                        .equals(learningResourcePersistence.getById(consideredResult.getAssociatedLearningResourceId()).getValue().getDefinitionId()
                        ));
    }

    @Override
    public WrapperResult<Double> getCourseProgressFactor(Course course, Student student) {
        Set<LearningResourceDefinitionId> learningResourceDefinitionIds = course.getLessons().stream()
                .flatMap(o -> o.getSegments().stream()).map(Segment::getLearningResourceDefinitionId).collect(Collectors.toSet());
        List<LearningResult> studentsLearningResultsFromCourse = learningResultPersistence
                .getLearningResultsOfStudentByLearningResourceDefinitionIds(student.getId(), learningResourceDefinitionIds).getValue();
        List<LearningResult> passedDistinctResults = new ArrayList<>();
        for (LearningResult learningResult : studentsLearningResultsFromCourse) {
            if (resultsContainResultOfSameAssociatedDefinition(passedDistinctResults, learningResult)) {
                continue;
            }
            if (learningResult.isSuccessful())
                passedDistinctResults.add(learningResult);
        }
        return WrapperResult.successWrapper(Math.floor((double) passedDistinctResults.size() / learningResourceDefinitionIds.size() * 100) / 100);
    }
}
