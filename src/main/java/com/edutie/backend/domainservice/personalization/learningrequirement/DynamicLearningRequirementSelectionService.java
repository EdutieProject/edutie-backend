package com.edutie.backend.domainservice.personalization.learningrequirement;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.student.Student;
import validation.WrapperResult;

import java.util.Set;

public interface DynamicLearningRequirementSelectionService {
    WrapperResult<Set<LearningRequirement>> selectRequirementsForStudent(Student student);
}
