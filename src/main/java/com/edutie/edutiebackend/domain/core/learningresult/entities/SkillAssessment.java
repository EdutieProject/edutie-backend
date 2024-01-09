package com.edutie.edutiebackend.domain.core.learningresult.entities;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningAssessmentId;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class SkillAssessment extends EntityBase<LearningAssessmentId> {
    @Setter
    private int points;
    @ManyToOne(targetEntity = Skill.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    @JsonIgnore
    private Skill skill;
    @Setter
    @Column(name = "skill_id")
    private SkillId skillId;

    public static SkillAssessment create(SkillId skillId, int pointsValue) {
        var assessment = new SkillAssessment();
        assessment.setPoints(pointsValue);
        assessment.setSkillId(skillId);
        return assessment;
    }
}
