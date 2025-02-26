package com.edutie.domain.service.personalization.learningresult.schema;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.personalization.common.PersonalizationSchema;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.domain.core.learning.solutionsubmission.SolutionSubmission;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.learning.student.Student;
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
     * @param learningExperience   learning resource which is the subject of the assessment
     * @param solutionSubmission solution submission
     * @return new Assessment Schema
     */
    public static AssessmentSchema create(LearningExperience learningExperience, SolutionSubmission solutionSubmission) {
        AssessmentSchema assessmentSchema = new AssessmentSchema();
        assessmentSchema.setStudent(solutionSubmission.getStudent());
        assessmentSchema.setSolutionSubmission(solutionSubmission);
        assessmentSchema.setQualifiedRequirements(learningExperience.getQualifiedRequirements());
        assessmentSchema.setLearningResourceDefinitionId(learningExperience.getDefinitionId());
        assessmentSchema.setActivityText(learningExperience.getActivity().getActivityText());
        return assessmentSchema;
    }

    @Override
    public Set<PersonalizationRule<?>> getPersonalizationRules() {
        return Set.of(); //TODO!
    }
}
