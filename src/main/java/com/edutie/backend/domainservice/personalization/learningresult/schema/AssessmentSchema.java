package com.edutie.backend.domainservice.personalization.learningresult.schema;

import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
     * @param solutionSubmission solution submission
     * @return new Assessment Schema
     */
    public static AssessmentSchema create(SolutionSubmission solutionSubmission) {
        AssessmentSchema assessmentSchema = new AssessmentSchema();
        assessmentSchema.setStudent(solutionSubmission.getStudent());
        assessmentSchema.setSolutionSubmission(solutionSubmission);
        assessmentSchema.setQualifiedRequirements(solutionSubmission.getLearningResource().getQualifiedRequirements());
        assessmentSchema.setLearningResourceDefinitionId(solutionSubmission.getLearningResource().getDefinitionId());
        return assessmentSchema;
    }

    @JsonProperty("activityText")
    public String getActivityText() {
        return solutionSubmission.getLearningResource().getActivity().getActivityText();
    }
}
