package com.edutie.backend.domain.personalization.learningresult.entities;

import com.edutie.backend.api.serialization.serializers.IdOnlyCollectionSerializer;
import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.learningrequirement.entities.SubRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresult.identities.AssessmentId;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Assessment extends EntityBase<AssessmentId> {
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "learning_requirement_id"))
    LearningRequirementId learningRequirementId;
    @Embedded
    @AttributeOverride(name = "gradeNumber", column = @Column(name = "grade_number"))
    Grade grade;
    @Column(columnDefinition = "TEXT")
    String feedbackText;
    @JsonSerialize(using = IdOnlyCollectionSerializer.class)
    @ManyToMany(targetEntity = SubRequirement.class, fetch = FetchType.EAGER)
    List<SubRequirement> qualifiedSubRequirements = new ArrayList<>();

    /**
     * Creates an assessment based on provided data
     *
     * @param learningRequirementId    assessed learning requirement id
     * @param grade                    assessment grade
     * @param feedbackText             feedback text
     * @param qualifiedSubRequirements qualified sub requirements list
     * @return new Assessment
     */
    public static Assessment create(LearningRequirementId learningRequirementId, Grade grade, String feedbackText, List<SubRequirement> qualifiedSubRequirements) {
        Assessment assessment = new Assessment();
        assessment.setId(new AssessmentId());
        assessment.setLearningRequirementId(learningRequirementId);
        assessment.setGrade(grade);
        assessment.setFeedbackText(feedbackText);
        assessment.setQualifiedSubRequirements(qualifiedSubRequirements);
        return assessment;
    }

}
