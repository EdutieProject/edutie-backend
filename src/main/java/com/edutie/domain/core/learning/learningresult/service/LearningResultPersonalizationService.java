package com.edutie.domain.core.learning.learningresult.service;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
import com.edutie.domain.core.learning.student.Student;
import validation.WrapperResult;

public interface LearningResultPersonalizationService {
    <T extends SolutionSubmission> WrapperResult<LearningResult<T>> createPersonalized(Student student, LearningExperience<?> learningExperience, T solutionSubmission);
}
