package com.edutie.backend.domain.personalization.learningresourcegenerationschema.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.identities.ProblemDescriptorId;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProblemDescriptor extends EntityBase<ProblemDescriptorId> {
    private final LearningRequirementId learningRequirementId;
    private final KnowledgeSubjectId knowledgeSubjectId;
    @Setter
    private int qualifiedSubRequirements = 0;
    private final List<PersonalizationRule> personalizationRules = new ArrayList<>();

    public ProblemDescriptor(LearningRequirement learningRequirement) {
        this.setId(new ProblemDescriptorId());
        this.learningRequirementId = learningRequirement.getId();
        this.knowledgeSubjectId = learningRequirement.getKnowledgeSubjectId();
    }

    public void addPersonalizationRule(PersonalizationRule personalizationRule) {
        personalizationRules.add(personalizationRule);
    }

    /**
     * Mutates the problem descriptor assigning it the calculated qualified sub-requirements
     * amount.
     */
    public void calculateQualifiedSubRequirements(int subRequirementsSize) {
        double meanOverallGrade = personalizationRules.stream()
                .flatMap(o -> o.getLearningResults().stream())
                .flatMap(o-> o.getAssessments().stream())
                .map(o-> o.getGrade().gradeNumber()).mapToInt(Integer::intValue)
                .average().orElse(0.0);
        double gradePercentage = meanOverallGrade / Grade.MAX_GRADE.gradeNumber();
        this.qualifiedSubRequirements = (int) Math.ceil(gradePercentage * subRequirementsSize);
    }
}
