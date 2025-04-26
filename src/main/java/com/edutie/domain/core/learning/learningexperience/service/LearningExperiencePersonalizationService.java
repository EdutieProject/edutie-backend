package com.edutie.domain.core.learning.learningexperience.service;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.student.Student;
import validation.WrapperResult;

public interface LearningExperiencePersonalizationService {
    WrapperResult<LearningExperience<?>> createPersonalized(Student student, KnowledgeOrigin knowledgeOrigin, ElementalRequirement elementalRequirement);
}
