package com.edutie.domain.service.personalization.learningrequirement;

import com.edutie.domain.core.education.learningrequirement.LearningRequirement;
import com.edutie.domain.core.learning.student.Student;
import validation.WrapperResult;

import java.util.Set;

public interface DynamicLearningRequirementSelectionService {
    WrapperResult<Set<LearningRequirement>> selectRequirementsForStudent(Student student);
}
