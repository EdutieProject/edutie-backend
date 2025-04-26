package com.edutie.application.management.learningsubject;

import com.edutie.application.common.UseCaseHandler;
import com.edutie.application.management.learningsubject.command.ScaffoldLearningSubjectRequirementsCommand;
import com.edutie.application.management.learningsubject.command.SetLearningSubjectKnowledgeSubjectCommand;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import validation.WrapperResult;

public interface ScaffoldLearningSubjectRequirementsCommandHandler extends UseCaseHandler<WrapperResult<LearningSubject>, ScaffoldLearningSubjectRequirementsCommand> {
}
