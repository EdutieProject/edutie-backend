package com.edutie.backend.domainservice.personalization.learningresult.schema;

import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.common.PersonalizationSchema;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
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
public class AssessmentSchema implements PersonalizationSchema {
    private Set<ElementalRequirement> qualifiedRequirements = new HashSet<>();
    @JsonIgnore
    private Student student;
    private SolutionSubmission solutionSubmission;
    private LearningResourceDefinitionId learningResourceDefinitionId;
    private String activityText;

    /**
     * Creates assessment schema
     *
     * @param learningResource   learning resource which is the subject of the assessment
     * @param solutionSubmission solution submission
     * @return new Assessment Schema
     */
    public static AssessmentSchema create(LearningResource learningResource, SolutionSubmission solutionSubmission) {
        AssessmentSchema assessmentSchema = new AssessmentSchema();
        assessmentSchema.setStudent(solutionSubmission.getStudent());
        assessmentSchema.setSolutionSubmission(solutionSubmission);
        assessmentSchema.setQualifiedRequirements(learningResource.getQualifiedRequirements());
        assessmentSchema.setLearningResourceDefinitionId(learningResource.getDefinitionId());
        assessmentSchema.setActivityText(learningResource.getActivity().getActivityText());
        return assessmentSchema;
    }

    @Override
    public Set<PersonalizationRule<?>> getPersonalizationRules() {
        return Set.of(); //TODO!
    }
}
