package com.edutie.domain.core.education.learningsubject.service;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.entities.LearningSubjectRequirement;
import validation.WrapperResult;

public interface DynamicRequirementSelectionService {
    WrapperResult<LearningSubjectRequirement> chooseRequirement(LearningSubject learningSubject);
}
