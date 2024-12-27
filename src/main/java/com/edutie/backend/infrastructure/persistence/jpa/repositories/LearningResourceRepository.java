package com.edutie.backend.infrastructure.persistence.jpa.repositories;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface LearningResourceRepository extends JpaRepository<LearningResource, LearningResourceId> {
	List<LearningResource> getAllByDefinitionId(LearningResourceDefinitionId definitionId);
	List<LearningResource> getAllByStudentId(StudentId studentId);
}
