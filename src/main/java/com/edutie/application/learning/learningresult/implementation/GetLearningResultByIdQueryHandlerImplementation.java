package com.edutie.application.learning.learningresult.implementation;

import com.edutie.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.application.learning.learningresult.query.GetLearningResultByIdQuery;
import com.edutie.application.learning.learningresult.view.LearningResultView;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetLearningResultByIdQueryHandlerImplementation implements GetLearningResultByIdQueryHandler {
    private final LearningResultPersistence learningResultPersistence;
    private final LearningSubjectPersistence learningSubjectPersistence;

    @Override
    public WrapperResult<LearningResultView<?>> handle(GetLearningResultByIdQuery query) {
        log.info("Retrieving learning result by id {} for user of id {}", query.learningResultId(), query.studentUserId());
        LearningResult<?> learningResult = learningResultPersistence.getById(query.learningResultId()).getValue();
        ElementalRequirementId elementalRequirementId = learningResult.getLearningEvaluation()
                .getAssessments().stream().findFirst().get().getElementalRequirementSnapshot().getElementalRequirementId();
        LearningSubject learningSubject = learningSubjectPersistence.getLearningSubjectByElementalRequirementId(elementalRequirementId).getValue();
        return WrapperResult.successWrapper(
                new LearningResultView<>(learningResult, learningSubject.getId(), learningSubject.getName())
        );
    }
}
