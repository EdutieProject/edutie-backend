package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.domain.core.learning.student.identities.StudentId;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface LearningResourceRepository extends JpaRepository<LearningExperience, LearningExperienceId> {
	List<LearningExperience> getAllByDefinitionId(LearningResourceDefinitionId definitionId);
	List<LearningExperience> getAllByStudentIdOrderByCreatedOnDesc(StudentId studentId, Limit limit);
}
