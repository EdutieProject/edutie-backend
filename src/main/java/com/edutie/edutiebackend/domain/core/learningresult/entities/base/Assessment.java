package com.edutie.edutiebackend.domain.core.learningresult.entities.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.entities.LearningAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.entities.SkillAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.identities.AssessmentId;
import com.edutie.edutiebackend.domain.core.learningrequirement.LearningRequirement;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Assessment<TAssessedEntity> extends EntityBase<AssessmentId> {
    @Column(name = "assessment_points")
    int assessmentPoints;
    @ManyToOne(fetch = FetchType.EAGER)
    TAssessedEntity entity;

    public static LearningAssessment create(LearningRequirement learningRequirement, int pointsValue) {
        var assessment = new LearningAssessment();
        assessment.setId(new AssessmentId());
        assessment.setEntity(learningRequirement);
        assessment.setAssessmentPoints(pointsValue);
        return assessment;
    }
    public static SkillAssessment create(Skill skill, int pointsValue) {
        var assessment = new SkillAssessment();
        assessment.setId(new AssessmentId());
        assessment.setEntity(skill);
        assessment.setAssessmentPoints(pointsValue);
        return assessment;
    }
}
