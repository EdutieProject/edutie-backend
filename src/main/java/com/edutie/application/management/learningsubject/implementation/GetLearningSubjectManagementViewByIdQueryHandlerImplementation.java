package com.edutie.application.management.learningsubject.implementation;

import com.edutie.application.management.learningsubject.GetLearningSubjectManagementViewByIdQueryHandler;
import com.edutie.application.management.learningsubject.query.GetLearningSubjectByIdQuery;
import com.edutie.application.management.learningsubject.view.LearningSubjectManagementView;
import com.edutie.domain.core.education.knowledgesubject.view.KnowledgeSubjectDetailsView;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.GetKnowledgeSubjectDetailsService;
import com.edutie.infrastructure.knowledgemap.knowledgesubject.schema.GetKnowledgeSubjectDetailsSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetLearningSubjectManagementViewByIdQueryHandlerImplementation implements GetLearningSubjectManagementViewByIdQueryHandler {
    private final LearningSubjectPersistence learningSubjectPersistence;
    private final GetKnowledgeSubjectDetailsService getKnowledgeSubjectDetailsService;

    @Override
    public WrapperResult<LearningSubjectManagementView> handle(GetLearningSubjectByIdQuery query) {
        log.info("Retrieving learning subject by its id {} for educator user of id {}", query.learningSubjectId(), query.educatorUserId());
        LearningSubject learningSubject = learningSubjectPersistence.getById(query.learningSubjectId()).getValue();
        if (learningSubject.isKnowledgeOriginEmpty())
            return WrapperResult.successWrapper(new LearningSubjectManagementView(learningSubject, null));
        KnowledgeSubjectDetailsView knowledgeSubjectDetails = getKnowledgeSubjectDetailsService
                .getDetails(new GetKnowledgeSubjectDetailsSchema(learningSubject.getKnowledgeOrigin().getKnowledgeSubjectId())).getValue();
        return WrapperResult.successWrapper(new LearningSubjectManagementView(learningSubject, knowledgeSubjectDetails));
    }
}
