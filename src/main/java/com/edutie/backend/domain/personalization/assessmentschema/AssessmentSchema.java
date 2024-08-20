package com.edutie.backend.domain.personalization.assessmentschema;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.assessmentschema.entities.ProblemDescriptor;
import com.edutie.backend.domain.personalization.assessmentschema.identities.AssessmentSchemaId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Entity
public class AssessmentSchema extends AuditableEntityBase<AssessmentSchemaId> {
    @JsonIgnore
    private Student student;
    private SolutionSubmission solutionSubmission;
    private Set<ProblemDescriptor> problemDescriptors = new HashSet<>();

    public static AssessmentSchema create(Student student, SolutionSubmission solutionSubmission, LearningResource learningResource) {
        AssessmentSchema assessmentSchema = new AssessmentSchema();
        assessmentSchema.setStudent(student);
        assessmentSchema.setSolutionSubmission(solutionSubmission);
        assessmentSchema.setProblemDescriptors(learningResource.getProblemDetails().stream().map(ProblemDescriptor::new).collect(Collectors.toSet()));
        return assessmentSchema;
    }
}
