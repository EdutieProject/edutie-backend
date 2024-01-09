package com.edutie.edutiebackend.domain.core.learningresult.entities;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningAssessmentId;
import com.edutie.edutiebackend.domain.core.lessonsegment.entities.LearningRequirement;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class LearningAssessment extends EntityBase<LearningAssessmentId> {
    @Setter
    private int points;
    @ManyToOne(targetEntity = LearningRequirement.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "learning_requirement_id", insertable = false, updatable = false)
    @JsonIgnore
    private LearningRequirement learningRequirement;
    @Setter
    @Column(name = "learning_requirement_id")
    private LearningRequirementId learningRequirementId;

    public static LearningAssessment create(LearningRequirementId learningRequirementId, int pointsValue) {
        var assessment = new LearningAssessment();
        assessment.setPoints(pointsValue);
        assessment.setLearningRequirementId(learningRequirementId);
        return assessment;
    }
}
