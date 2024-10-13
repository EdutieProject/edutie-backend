package com.edutie.backend.domain.personalization.assessmentschema;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.assessmentschema.entities.AssessmentSchemaProblemDescriptor;
import com.edutie.backend.domain.personalization.assessmentschema.identities.AssessmentSchemaId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AssessmentSchema extends EntityBase<AssessmentSchemaId> {
    private Set<AssessmentSchemaProblemDescriptor> problemDescriptors = new HashSet<>();
    @JsonIgnore
    private Student student;
    private SolutionSubmission solutionSubmission;
    private LearningResourceDefinition learningResourceDefinition;

    /**
     * Creates assessment schema
     *
     * @param student            student profile
     * @param solutionSubmission solution submission
     * @param learningResource   learning resource
     * @return new Assessment Schema
     */
    public static AssessmentSchema create(Student student, SolutionSubmission solutionSubmission, LearningResource learningResource) {
        AssessmentSchema assessmentSchema = new AssessmentSchema();
        assessmentSchema.setId(new AssessmentSchemaId());
        assessmentSchema.setStudent(student);
        assessmentSchema.setSolutionSubmission(solutionSubmission);
        assessmentSchema.setProblemDescriptors(learningResource.getQualifiedRequirements().stream().map(AssessmentSchemaProblemDescriptor::new).collect(Collectors.toSet()));
        assessmentSchema.setLearningResourceDefinition(learningResource.getDefinition());
        return assessmentSchema;
    }

    public AssessmentSchemaProblemDescriptor getProblemDescriptorByLearningRequirement(LearningRequirementId learningRequirementId) {
        return problemDescriptors.stream().filter(o -> o.getLearningRequirementId().equals(learningRequirementId)).findFirst().get();
    }
}
