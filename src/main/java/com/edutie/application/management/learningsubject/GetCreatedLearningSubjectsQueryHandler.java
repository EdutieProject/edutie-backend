package com.edutie.application.management.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.learningsubject.query.GetCreatedLearningSubjectsQuery;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import validation.WrapperResult;

import java.util.List;

public interface GetCreatedLearningSubjectsQueryHandler
        extends UseCaseHandler<WrapperResult<List<LearningSubject>>, GetCreatedLearningSubjectsQuery> {
}
