package com.edutie.domain.core.learning.learningresult.entities;

import com.edutie.api.serialization.serializers.IdOnlyCollectionSerializer;
import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import com.edutie.domain.core.personalization.PersonalizationError;
import com.edutie.domain.core.learning.learningresult.identities.AssessmentId;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.OperationFailureException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Assessment extends EntityBase<AssessmentId> {
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "learning_requirement_id"))
    private LearningRequirementId learningRequirementId;
    @Embedded
    @AttributeOverride(name = "gradeNumber", column = @Column(name = "grade_number"))
    private Grade grade;
    @AttributeOverride(name = "text", column = @Column(columnDefinition = "TEXT", name = "feedback_text"))
    private Feedback feedback;
    @JsonSerialize(using = IdOnlyCollectionSerializer.class)
    @ManyToMany(targetEntity = ElementalRequirement.class, fetch = FetchType.EAGER)
    private List<ElementalRequirement> qualifiedElementalRequirements = new ArrayList<>();

    /**
     * Creates an assessment based on provided data
     *
     * @param learningRequirementId          assessed learning requirement id
     * @param grade                          assessment grade
     * @param feedback                   feedback text
     * @param qualifiedElementalRequirements qualified sub requirements list
     * @return new Assessment
     */
    public static Assessment create(LearningRequirementId learningRequirementId, Grade grade, Feedback feedback, List<ElementalRequirement> qualifiedElementalRequirements) {
        Assessment assessment = new Assessment();
        assessment.setId(new AssessmentId());
        assessment.setLearningRequirementId(learningRequirementId);
        assessment.setGrade(grade);
        assessment.setFeedback(feedback);
        assessment.setQualifiedElementalRequirements(qualifiedElementalRequirements);
        return assessment;
    }

    protected LearningSubject getCorrespondingLearningRequirement() {
        return qualifiedElementalRequirements.getFirst().getLearningRequirement();
    }

    @JsonProperty("learningRequirementName")
    public String getLearningRequirementName() {
        return getCorrespondingLearningRequirement().getName();
    }

    /**
     * Returns a difficulty factor as double. This represents the percentage of fulfilling the
     * whole learning requirement.
     * @return difficulty factor as double.
     */
    @JsonProperty("difficultyFactor")
    public double getDifficultyFactor() {
        return (double) Math.round(
                (this.qualifiedElementalRequirements.stream().mapToInt(ElementalRequirement::getOrdinal)
                        .max().orElseThrow(() -> new OperationFailureException(PersonalizationError.invalidDifficultyCalculation(this.getId()))) + 1F)
                        / getCorrespondingLearningRequirement().getRequirements().size() * 100)
                / 100;
    }

}
