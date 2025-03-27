package com.edutie.domain.core.learning.learningresult.entities;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.learning.learningresult.identities.AssessmentId;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Assessment extends EntityBase<AssessmentId> {
    int masteryPointsAmount = -1;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "elemental_requirement_id"))
    //todo: elemental requirement snapshot - to contain requirement name at the moment of assessment creation
    private ElementalRequirementId elementalRequirementId;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(columnDefinition = "TEXT", name = "feedback_text"))
    private Feedback feedback;

    public static Assessment create(Feedback feedback, ElementalRequirementId elementalRequirementId, int masteryPointsAmount) {
        Assessment assessment = new Assessment();
        assessment.elementalRequirementId = elementalRequirementId;
        assessment.masteryPointsAmount = masteryPointsAmount;
        assessment.feedback = feedback;
        return assessment;
    }
}
