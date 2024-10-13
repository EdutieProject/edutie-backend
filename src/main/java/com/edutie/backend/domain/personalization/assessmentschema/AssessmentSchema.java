package com.edutie.backend.domain.personalization.assessmentschema;

import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Schema used in assessment process.
 */
@NoArgsConstructor
@Getter
@Setter
public class AssessmentSchema {
    private Set<ElementalRequirement> qualifiedRequirements = new HashSet<>();
    @JsonIgnore
    private Student student;
    private SolutionSubmission solutionSubmission;
    private LearningResourceDefinitionId learningResourceDefinitionId;

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
        assessmentSchema.setStudent(student);
        assessmentSchema.setSolutionSubmission(solutionSubmission);
        assessmentSchema.setQualifiedRequirements(solutionSubmission.getLearningResource().getQualifiedRequirements());
        assessmentSchema.setLearningResourceDefinitionId(solutionSubmission.getLearningResource().getDefinitionId());
        return assessmentSchema;
    }
}
