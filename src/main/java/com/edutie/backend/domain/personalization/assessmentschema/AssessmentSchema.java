package com.edutie.backend.domain.personalization.assessmentschema;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.assessmentschema.entities.AssessmentSchemaProblemDescriptor;
import com.edutie.backend.domain.personalization.assessmentschema.identities.AssessmentSchemaId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Schema used in assessment process.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AssessmentSchema extends AuditableEntityBase<AssessmentSchemaId> {
	private Set<AssessmentSchemaProblemDescriptor> problemDescriptors = new HashSet<>();
	@JsonIgnore
	private Student student;
	private SolutionSubmission solutionSubmission;

	public static AssessmentSchema create(Student student, SolutionSubmission solutionSubmission, LearningResource learningResource) {
		AssessmentSchema assessmentSchema = new AssessmentSchema();
		assessmentSchema.setStudent(student);
		assessmentSchema.setSolutionSubmission(solutionSubmission);
		assessmentSchema.setProblemDescriptors(learningResource.getProblemDescriptors().stream().map(AssessmentSchemaProblemDescriptor::new).collect(Collectors.toSet()));
		return assessmentSchema;
	}
}
