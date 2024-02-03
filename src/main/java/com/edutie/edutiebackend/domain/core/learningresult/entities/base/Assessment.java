package com.edutie.edutiebackend.domain.core.learningresult.entities.base;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.entities.LearningAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.entities.SkillAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.identities.AsessmentId;
import com.edutie.edutiebackend.domain.core.lessonsegment.entities.LearningRequirement;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.student.entites.IntelligenceLearningParameter;
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
public abstract class Assessment<TAssessment> extends EntityBase<AsessmentId> {
    @Column(name = "assessment_points")
    int points;
    @ManyToOne(fetch = FetchType.EAGER)
    TAssessment entity;

    public static LearningAssessment create(LearningRequirement learningRequirement, int pointsValue) {
        var assessment = new LearningAssessment();
        assessment.setId(new AsessmentId());
        assessment.setEntity(learningRequirement);
        assessment.setPoints(pointsValue);
        return assessment;
    }
    public static SkillAssessment create(Skill skill, int pointsValue) {
        var assessment = new SkillAssessment();
        assessment.setId(new AsessmentId());
        assessment.setEntity(skill);
        assessment.setPoints(pointsValue);
        return assessment;
    }
}
