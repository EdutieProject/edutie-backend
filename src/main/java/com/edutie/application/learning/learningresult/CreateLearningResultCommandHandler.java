package com.edutie.application.learning.learningresult;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.learning.learningresult.command.CreateLearningResultCommand;
import com.edutie.application.learning.learningresult.view.LearningResultView;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
import validation.WrapperResult;

public interface CreateLearningResultCommandHandler<TSubmission extends SolutionSubmission>
        extends UseCaseHandler<WrapperResult<LearningResultView<TSubmission>>, CreateLearningResultCommand<TSubmission>> {
}
