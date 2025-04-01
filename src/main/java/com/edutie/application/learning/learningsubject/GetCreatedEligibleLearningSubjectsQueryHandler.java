package com.edutie.application.learning.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningsubject.query.GetCreatedEligibleLearningSubjectsQuery;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import validation.WrapperResult;

import java.util.List;

public interface GetCreatedEligibleLearningSubjectsQueryHandler
        extends UseCaseHandler<WrapperResult<List<LearningSubject>>, GetCreatedEligibleLearningSubjectsQuery> {
}
